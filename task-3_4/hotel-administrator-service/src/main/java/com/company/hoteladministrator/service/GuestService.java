package com.company.hoteladministrator.service;

import com.company.hoteladministrator.api.dao.*;
import com.company.hoteladministrator.api.exception.OperationCancelledException;
import com.company.hoteladministrator.api.mapper.IGuestMapper;
import com.company.hoteladministrator.api.service.IGuestService;
import com.company.hoteladministrator.api.service.IRoomAssignmentService;
import com.company.hoteladministrator.model.*;
import com.company.hoteladministrator.model.dto.AccommodateGuestDto;
import com.company.hoteladministrator.model.dto.GuestDto;
import com.company.hoteladministrator.model.enums.RoomAssignmentStatus;
import com.company.hoteladministrator.model.enums.RoomStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class GuestService implements IGuestService {
    private IGuestDao guestDao;
    private IRoomDao roomDao;
    private IRoomAssignmentDao roomAssignmentDao;
    private IMaintenanceDao maintenanceDao;
    private IOrderedMaintenanceDao orderedMaintenanceDao;
    private IRoomAssignmentService roomAssignmentService;
    private IGuestMapper guestMapper;
    @Value("${allowedNumberOfNotes:10}")
    private int allowedNumberOfNotes;

    private static final Logger log = Logger.getLogger(GuestService.class.getName());

    @Autowired
    private GuestService(IGuestDao guestDao, IRoomDao roomDao, IRoomAssignmentDao roomAssignmentDao,
                         IMaintenanceDao maintenanceDao, IOrderedMaintenanceDao orderedMaintenanceDao,
                         IRoomAssignmentService roomAssignmentService, IGuestMapper guestMapper) {
        this.guestDao = guestDao;
        this.roomDao = roomDao;
        this.roomAssignmentDao = roomAssignmentDao;
        this.maintenanceDao = maintenanceDao;
        this.orderedMaintenanceDao = orderedMaintenanceDao;
        this.roomAssignmentService = roomAssignmentService;
        this.guestMapper = guestMapper;
    }

    @Override
    @Transactional
    public GuestDto addGuest(GuestDto guestDto) {
        Guest entity = guestMapper.toEntity(guestDto);
        guestDao.save(entity);
        return guestMapper.toDto(entity);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, isolation = Isolation.READ_UNCOMMITTED)
    public void accommodateToRoom(AccommodateGuestDto accommodateGuestDto) {
        Guest guestToFlip = guestDao.getById(accommodateGuestDto.getGuestId());
        Room roomToFlip = roomDao.getById(accommodateGuestDto.getRoomId());
        if (guestToFlip == null || roomToFlip == null) {
            log.warn("Incorrect input when trying to accommodate a guest");
            throw new IllegalArgumentException("Guest or room not found");
        }
        if (roomToFlip.getRoomStatus().equals(RoomStatus.FREE)) {
            RoomAssignment roomAssignment = new RoomAssignment(roomToFlip, guestToFlip,
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()
                    .plusDays(accommodateGuestDto.getNumberOfDays().longValue())), RoomAssignmentStatus.ACTIVE,
                    Timestamp.valueOf(LocalDateTime.now()));
            roomToFlip.addRoomAssignment(roomAssignment);
            guestToFlip.addRoomAssignment(roomAssignment);
            roomAssignmentDao.save(roomAssignment);
            if (roomToFlip.getNumberOfBeds().equals(
                    roomAssignmentDao.getActiveRoomAssignmentsByRoomId(roomToFlip.getId()).size())) {
                roomToFlip.setRoomStatus(RoomStatus.OCCUPIED);
            }
            guestDao.update(guestToFlip);
            roomDao.update(roomToFlip);
        } else {
            log.warn("Failed to accommodate guest to room");
            throw new OperationCancelledException("Unfortunately, this room is " +
                    roomToFlip.getRoomStatus().toString().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    @Transactional
    public void evictFromRoom(Long guestId) {
        Guest guestToEvict = guestDao.getById(guestId);
        if (guestToEvict == null) {
            log.warn("Incorrect input when trying to evict guest from room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            guestToEvict.getRoomAssignments().stream()
                    .filter(a -> RoomAssignmentStatus.ACTIVE.equals(a.getRoomAssignmentStatus()))
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        Room roomToEvictFrom = roomDao.getById(roomAssignment.getRoom().getId());
                        roomToEvictFrom.setRoomStatus(RoomStatus.FREE);
                        roomAssignment.setRoomAssignmentStatus(RoomAssignmentStatus.CLOSED);
                        roomAssignment.setCheckOutDate(Timestamp.valueOf(LocalDateTime.now()));
                        roomAssignmentDao.update(roomAssignment);
                    },
                    () -> {
                        log.warn("Failed to evict guest from room");
                        throw new OperationCancelledException("Guest does not live in any of the rooms");
                    });
        }
    }

    @Override
    @Transactional
    public void orderMaintenance(Long guestId, Long maintenanceId) {
        Guest guestToOrderMaintenance = guestDao.getById(guestId);
        Maintenance maintenanceToOrder = maintenanceDao.getById(maintenanceId);
        if (guestToOrderMaintenance == null || maintenanceToOrder == null) {
            log.warn("Incorrect input when trying to order maintenance");
            throw new IllegalArgumentException("Guest or maintenance not found");
        } else {
            roomAssignmentDao.getActiveRoomAssignmentsByGuestId(
                    guestToOrderMaintenance.getId()).stream()
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        OrderedMaintenance orderedMaintenance = new OrderedMaintenance(roomAssignment, maintenanceToOrder, Timestamp.valueOf(LocalDateTime.now()));
                        roomAssignment.addMaintenance(orderedMaintenance);
                        orderedMaintenanceDao.save(orderedMaintenance);
                        roomAssignmentDao.update(roomAssignment);
                    },
                    () -> {
                        log.warn("Failed to order maintenance");
                        throw new OperationCancelledException("Guest does not live in the hotel");
                    });
        }
    }

    @Override
    @Transactional
    public Double getAmountOfPaymentForTheRoom(Long guestId) {
        Guest guestToGetAmount = guestDao.getById(guestId);
        if (guestToGetAmount == null) {
            log.warn("Incorrect input when trying to get amount of payment for room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            Double amountOfPaymentForTheRoom = 0.0;
            if (!roomAssignmentDao.getActiveRoomAssignmentsByGuestId(
                    guestToGetAmount.getId()).isEmpty()) {
                for (RoomAssignment roomAssignment : roomAssignmentDao.getActiveRoomAssignmentsByGuestId(
                        guestToGetAmount.getId())) {
                    amountOfPaymentForTheRoom = roomAssignmentService.getPricePerStay(roomAssignment);
                }
            }
            return amountOfPaymentForTheRoom;
        }
    }

    @Override
    @Transactional
    public GuestDto changeGuestInfo(GuestDto guestDto) {
        Guest guestToChange = guestDao.getById(guestDto.getId());
        if (guestToChange == null) {
            log.warn("Incorrect input when trying to change maintenance price");
            throw new IllegalArgumentException("Maintenance not found!");
        } else {
            guestToChange.setAge(guestDto.getAge());
            guestToChange.setName(guestDto.getName());
            guestToChange.setSurname(guestDto.getSurname());
            guestDao.update(guestToChange);
        }
        return guestMapper.toDto(guestDao.getById(guestDto.getId()));
    }

    @Override
    @Transactional
    public List<GuestDto> getSortedGuests(String paramToSort) {
        return guestDao.getSortedEntities(paramToSort).stream().map(guest ->
                guestMapper.toDto(guest)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<GuestDto> getAllGuests() {
        return guestDao.getAll().stream().map(guest ->
                guestMapper.toDto(guest)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GuestDto getGuestById(Long id) {
        return guestMapper.toDto(guestDao.getById(id));
    }

    @Override
    @Transactional
    public Integer getNumberOfGuests() {
        int totalNumberOfGuest = 0;
        for (GuestDto guest : getAllGuests()) {
            if (!roomAssignmentDao.getActiveRoomAssignmentsByGuestId(guest.getId()).isEmpty()) {
                totalNumberOfGuest++;
            }
        }
        return totalNumberOfGuest;
    }

    @Override
    @Transactional
    public List<GuestDto> getLastGuests(Long roomId) {
        return guestDao.getLastGuests(roomId, allowedNumberOfNotes).stream().map(guest ->
                guestMapper.toDto(guest)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteGuest(Long guestId) {
        guestDao.delete(guestDao.getById(guestId));
    }
}
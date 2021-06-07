package com.company.hoteladministrator.service;

import com.company.hoteladministrator.api.dao.IRoomDao;
import com.company.hoteladministrator.api.mapper.IRoomMapper;
import com.company.hoteladministrator.api.service.IRoomService;
import com.company.hoteladministrator.model.Room;
import com.company.hoteladministrator.model.dto.RoomDto;
import com.company.hoteladministrator.model.enums.RoomStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService implements IRoomService {

    private final IRoomDao roomDao;
    private final IRoomMapper roomMapper;
    @Value("${isRoomStatusChangeable:false}")
    private boolean isRoomStatusChangeable;

    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    @Autowired
    private RoomService(IRoomDao roomDao, IRoomMapper roomMapper) {
        this.roomDao = roomDao;
        this.roomMapper = roomMapper;
    }

    @Override
    public RoomDto addRoom(RoomDto roomDto) {
        Room entity = roomMapper.toEntity(roomDto);
        entity.setRoomStatus(RoomStatus.FREE);
        roomDao.save(entity);
        return roomMapper.toDto(entity);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return roomDao.getAll().stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto changeRoomInfo(RoomDto roomDto) {
        if (isRoomStatusChangeable) {
            Room roomToChange = roomDao.getById(roomDto.getId());
            if (roomToChange == null) {
                log.warn("Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Room not found!");
            } else {
                roomToChange.setRoomPrice(roomDto.getRoomPrice());
                roomToChange.setRoomNumber(roomDto.getRoomNumber());
                roomToChange.setNumberOfBeds(roomDto.getNumberOfBeds());
                roomToChange.setNumberOfStars(roomDto.getNumberOfStars());
                roomToChange.setRoomStatus(roomDto.getRoomStatus());
                roomDao.update(roomToChange);
            }
        } else {
            log.warn("Error when trying to change room status");
            throw new IllegalArgumentException("This function is not available at the moment");
        }
        return roomMapper.toDto(roomDao.getById(roomDto.getId()));
    }

    @Override
    public List<RoomDto> getAllFreeRooms() {
        return roomDao.getFreeRooms().stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return getAllFreeRooms().size();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDateTime requiredDate) {
        return roomDao.getFreeRoomsByDate(requiredDate);
    }

    @Override
    public RoomDto getRoomById(Long id) {
        return roomMapper.toDto(roomDao.getById(id));
    }

    @Override
    public List<RoomDto> getSortedRooms(String paramToSort) {
        return roomDao.getSortedEntities(paramToSort).stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getFreeSortedRooms(String paramToSort) {
        return roomDao.getFreeSortedRooms(paramToSort).stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomByGuestId(Long guestId) {
        return roomMapper.toDto(roomDao.getRoomByGuestId(guestId));
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomDao.delete(roomDao.getById(roomId));
    }
}
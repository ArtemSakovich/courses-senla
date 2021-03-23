package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.api.service.IGuestService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.*;
import com.company.util.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class GuestService implements IGuestService {
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private IRoomDao roomDao;
    @DependencyComponent
    private IRoomAssignmentDao roomAssignmentDao;
    @DependencyComponent
    private IMaintenanceDao maintenanceDao;
    private static final Logger log = Logger.getLogger(GuestService.class.getName());

    private GuestService() {

    }

    /**
     * The method is called by the execute() method from AddGuest[Action] class. Receives from it, the first name,
     * last name and age of the guest entered by the user through the console. A new object of the guest type is
     * created, and the same parameters are passed to its constructor. Also, guests ID is generated and sets to
     * new guest, after which the new guest will be saved in the DAO repository. The method returns a new guest object.
     * @param name guest's name
     * @param surname guest's surname
     * @param age guest's age
     * @return new guest object
     */
    @Override
    public Guest addGuest(String name, String surname, Integer age) {
        Guest guest = new Guest(name, surname, age);
        guest.setId(IdGenerator.getInstance().generateId(guestDao.getMaxId()));
        guestDao.save(guest);
        return guest;
    }

    @Override
    public void update(Guest updatedGuest) {
        guestDao.update(updatedGuest);
    }

    @Override
    public Guest getById(Long id) {
        return guestDao.getById(id);
    }

    public void accommodateToRoom(Long guestId, Long roomId, LocalDateTime checkOutDate) {
        Guest guestToFlip = guestDao.getById(guestId);
        Room roomToFlip = roomDao.getById(roomId);
        if (guestToFlip == null || roomToFlip == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to accommodate a guest");
            throw new IllegalArgumentException("Guest or room not found");
        }
        if (roomToFlip.getRoomStatus().equals(RoomStatus.FREE)) {
            RoomAssignment roomAssignment = new RoomAssignment(roomToFlip, guestToFlip, LocalDateTime.now(), checkOutDate,
                    RoomAssignmentStatus.ACTIVE);
            roomAssignment.setId(IdGenerator.getInstance().generateId(roomAssignmentDao.getMaxId()));
            roomToFlip.setRoomAssignment(roomAssignment);
            guestToFlip.setRoomAssignment(roomAssignment);
            roomAssignmentDao.save(roomAssignment);
            if (roomToFlip.getNumberOfBeds().equals(roomToFlip.getTenants().size())) {
                roomToFlip.setRoomStatus(RoomStatus.OCCUPIED);
            }
        } else {
            log.log(Level.SEVERE, "Failed to accommodate guest to room");
            throw new OperationCancelledException("Unfortunately, this room is " +
                    roomToFlip.getRoomStatus().toString().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public void evictFromRoom(Long guestId){
        Guest guestToEvict = guestDao.getById(guestId);
        if (guestToEvict == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to evict guest from room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            guestToEvict.getRoomAssignments().stream()
                    .filter(a -> RoomAssignmentStatus.ACTIVE.equals(a.getRoomAssignmentStatus()))
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        roomAssignment.getRoom().setRoomStatus(RoomStatus.FREE);
                        roomAssignment.setRoomAssignmentStatus(RoomAssignmentStatus.CLOSED);
                        roomAssignment.setCheckOutDate(LocalDateTime.now());
                    },
                    () -> {
                        log.log(Level.SEVERE, "Failed to evict guest from room");
                        throw new OperationCancelledException("Guest does not live in any of the rooms");
                    });
        }
    }

    @Override
    public void orderMaintenance(Long guestId, Long maintenanceId){
        Guest guestToOrderMaintenance = guestDao.getById(guestId);
        Maintenance maintenanceToOrder = maintenanceDao.getById(maintenanceId);
        if (guestToOrderMaintenance == null || maintenanceToOrder == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to order maintenance");
            throw new IllegalArgumentException("Guest or maintenance not found");
        } else {
            guestToOrderMaintenance.getActiveRoomAssignments().stream()
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        maintenanceToOrder.setOrderDate(LocalDate.now());
                        roomAssignment.setMaintenance(maintenanceToOrder);
                    },
                    () -> {
                        log.log(Level.SEVERE, "Failed to order maintenance");
                        throw new OperationCancelledException("Guest does not live in the hotel");
                    });
        }
    }

    @Override
    public Double getAmountOfPaymentForTheRoom(Long guestId) {
        Guest guestToGetAmount = guestDao.getById(guestId);
        if (guestToGetAmount == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to get amount of payment for room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            Double amountOfPaymentForTheRoom = 0.0;
            if (!guestToGetAmount.getActiveRoomAssignments().isEmpty()) {
                for (RoomAssignment roomAssignment : guestToGetAmount.getActiveRoomAssignments()) {
                    amountOfPaymentForTheRoom = roomAssignment.getPricePerStay();
                }
            }
            return amountOfPaymentForTheRoom;
        }
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestDao.getAll();
    }

    @Override
    public Integer getNumberOfGuests() {
        int totalNumberOfGuest = 0;
        for (Guest guest : getAllGuests()) {
            if(!guest.getActiveRoomAssignments().isEmpty()) {
                totalNumberOfGuest++;
            }
        }
        return totalNumberOfGuest;
    }

    @Override
    public List<Guest> sortGuestsABC() {
        List<Guest> guestsToSort = new ArrayList<>(getAllGuests());
        Collections.sort(guestsToSort);
        return guestsToSort;
    }
}
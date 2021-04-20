package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomAssignmentDao;
import com.company.api.dao.IRoomDao;
import com.company.api.exceptions.OperationCancelledException;
import com.company.api.service.IGuestService;
import com.company.api.service.IRoomAssignmentService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.*;
import com.company.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @DependencyComponent
    private IRoomAssignmentService roomAssignmentService;
    @DependencyComponent
    private DatabaseConnector databaseConnector;

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
        Connection connection = databaseConnector.getConnection();
        Guest guest = new Guest(name, surname, age);
        guestDao.save(connection, guest);
        return guest;
    }

    public void accommodateToRoom(Long guestId, Long roomId, LocalDateTime checkOutDate) {
        Connection connection = databaseConnector.getConnection();
        Guest guestToFlip = guestDao.getById(connection, guestId);
        Room roomToFlip = roomDao.getById(connection, roomId);
        if (guestToFlip == null || roomToFlip == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to accommodate a guest");
            throw new IllegalArgumentException("Guest or room not found");
        }
        if (roomToFlip.getRoomStatus().equals(RoomStatus.FREE)) {
            RoomAssignment roomAssignment = new RoomAssignment(roomToFlip.getId(), guestToFlip.getId(),
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(checkOutDate), RoomAssignmentStatus.ACTIVE);
            roomToFlip.addRoomAssignment(roomAssignment);
            guestToFlip.addRoomAssignment(roomAssignment);
            roomAssignmentDao.save(connection, roomAssignment);
            if (roomToFlip.getNumberOfBeds().equals(
                    roomAssignmentDao.getActiveRoomAssignmentsByRoomId(connection,
                            roomToFlip.getId()).size())) {
                roomToFlip.setRoomStatus(RoomStatus.OCCUPIED);
                try {
                    guestDao.update(connection, guestToFlip);
                    roomDao.update(connection, roomToFlip);
                    connection.commit();
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        log.log(Level.SEVERE, "Connection rollback has thrown an error", ex);
                    }
                    rollbackAnnouncer();
                }
            }
        } else {
            log.log(Level.SEVERE, "Failed to accommodate guest to room");
            throw new OperationCancelledException("Unfortunately, this room is " +
                    roomToFlip.getRoomStatus().toString().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public void evictFromRoom(Long guestId){
        Connection connection = databaseConnector.getConnection();
        Guest guestToEvict = guestDao.getById(connection, guestId);
        if (guestToEvict == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to evict guest from room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            guestToEvict.getRoomAssignments().stream()
                    .filter(a -> RoomAssignmentStatus.ACTIVE.equals(a.getRoomAssignmentStatus()))
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        Room roomToEvictFrom = roomDao.getById(connection, roomAssignment.getRoomId());
                        roomToEvictFrom.setRoomStatus(RoomStatus.FREE);
                        roomAssignment.setRoomAssignmentStatus(RoomAssignmentStatus.CLOSED);
                        roomAssignment.setCheckOutDate(Timestamp.valueOf(LocalDateTime.now()));
                        try {
                            guestDao.update(connection, guestToEvict);
                            roomDao.update(connection, roomToEvictFrom);
                            roomAssignmentDao.update(connection, roomAssignment);
                            connection.commit();
                        } catch (SQLException e) {
                            try {
                                connection.rollback();
                            } catch (SQLException ex) {
                                log.log(Level.SEVERE, "Connection rollback has thrown an error", ex);
                            }
                            rollbackAnnouncer();
                        }
                    },
                    () -> {
                        log.log(Level.SEVERE, "Failed to evict guest from room");
                        throw new OperationCancelledException("Guest does not live in any of the rooms");
                    });
        }
    }

    @Override
    public void orderMaintenance(Long guestId, Long maintenanceId){
        Connection connection = databaseConnector.getConnection();
        Guest guestToOrderMaintenance = guestDao.getById(connection, guestId);
        Maintenance maintenanceToOrder = maintenanceDao.getById(connection, maintenanceId);
        if (guestToOrderMaintenance == null || maintenanceToOrder == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to order maintenance");
            throw new IllegalArgumentException("Guest or maintenance not found");
        } else {
                roomAssignmentDao.getActiveRoomAssignmentsByGuestId(connection,
                        guestToOrderMaintenance.getId()).stream()
                        .findAny().ifPresentOrElse(roomAssignment -> {
                        maintenanceToOrder.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
                        roomAssignment.addMaintenance(maintenanceToOrder);
                            try {
                                guestDao.update(connection, guestToOrderMaintenance);
                                maintenanceDao.update(connection,maintenanceToOrder);
                                roomAssignmentDao.update(connection,roomAssignment);
                                connection.commit();
                            } catch (SQLException e) {
                                try {
                                    connection.rollback();
                                } catch (SQLException ex) {
                                    log.log(Level.SEVERE, "Connection rollback has thrown an error", ex);
                                }
                                rollbackAnnouncer();
                            }
                        },
                    () -> {
                        log.log(Level.SEVERE, "Failed to order maintenance");
                        throw new OperationCancelledException("Guest does not live in the hotel");
                    });
        }
    }

    @Override
    public Double getAmountOfPaymentForTheRoom(Long guestId) {
        Connection connection = databaseConnector.getConnection();
        Guest guestToGetAmount = guestDao.getById(connection, guestId);
        if (guestToGetAmount == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to get amount of payment for room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            Double amountOfPaymentForTheRoom = 0.0;
            if (!roomAssignmentDao.getActiveRoomAssignmentsByGuestId(connection,
                    guestToGetAmount.getId()).isEmpty()) {
                for (RoomAssignment roomAssignment : roomAssignmentDao.getActiveRoomAssignmentsByGuestId(databaseConnector.getConnection(),
                        guestToGetAmount.getId())) {
                    amountOfPaymentForTheRoom = roomAssignmentService.getPricePerStay(roomAssignment);
                }
            }
            return amountOfPaymentForTheRoom;
        }
    }

    @Override
    public List<Guest> getAllGuests() {
        Connection connection = databaseConnector.getConnection();
        return guestDao.getAll(connection);
    }

    @Override
    public Integer getNumberOfGuests() {
        Connection connection = databaseConnector.getConnection();
        int totalNumberOfGuest = 0;
        for (Guest guest : getAllGuests()) {
            if(!roomAssignmentDao.getActiveRoomAssignmentsByGuestId(connection,
                    guest.getId()).isEmpty()) {
                totalNumberOfGuest++;
            }
        }
        return totalNumberOfGuest;
    }

    @Override
    public List<Guest> getThreeLastGuests(Long roomId) {
        Connection connection = databaseConnector.getConnection();
        return guestDao.getThreeLastGuests(connection, roomId);
    }

    @Override
    public List<Guest> sortGuestsABC() {
        Connection connection = databaseConnector.getConnection();
        return guestDao.getSortedABCEntities(connection);
    }

    private void rollbackAnnouncer() {
        log.log(Level.SEVERE, "Connection was interrupted. Data has been rolled back.");
        throw new OperationCancelledException("Connection was interrupted. Data has been rolled back.");
    }
}
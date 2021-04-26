package com.company.service;

import com.company.api.dao.*;
import com.company.api.exceptions.OperationCancelledException;
import com.company.api.service.IGuestService;
import com.company.api.service.IRoomAssignmentService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.*;
import com.company.util.DatabaseConnector;
import com.company.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    private IOrderedMaintenanceDao orderedMaintenanceDao;
    @DependencyComponent
    private IRoomAssignmentService roomAssignmentService;
    @DependencyComponent
    private DatabaseConnector databaseConnector;
    @DependencyComponent
    private HibernateSessionFactory hibernateSessionFactory;

    private static final Logger log = Logger.getLogger(GuestService.class.getName());

    private GuestService() {

    }

    /**
     * The method is called by the execute() method from AddGuest[Action] class. Receives from it, the first name,
     * last name and age of the guest entered by the user through the console. A new object of the guest type is
     * created, and the same parameters are passed to its constructor. Also, guests ID is generated and sets to
     * new guest, after which the new guest will be saved in the DAO repository. The method returns a new guest object.
     *
     * @param name    guest's name
     * @param surname guest's surname
     * @param age     guest's age
     * @return new guest object
     */
    @Override
    public Guest addGuest(String name, String surname, Integer age) {
        Session session = hibernateSessionFactory.openSession();
        Guest guest = new Guest(name, surname, age);
        Transaction tx1 = session.beginTransaction();
        guestDao.save(session, guest);
        tx1.commit();
        session.close();
        return guest;
    }

    public void accommodateToRoom(Long guestId, Long roomId, LocalDateTime checkOutDate) {
        Session session = hibernateSessionFactory.openSession();
        Guest guestToFlip = guestDao.getById(session, guestId);
        Room roomToFlip = roomDao.getById(session, roomId);
        if (guestToFlip == null || roomToFlip == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to accommodate a guest");
            throw new IllegalArgumentException("Guest or room not found");
        }
        if (roomToFlip.getRoomStatus().equals(RoomStatus.FREE)) {
            RoomAssignment roomAssignment = new RoomAssignment(roomToFlip, guestToFlip,
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(checkOutDate), RoomAssignmentStatus.ACTIVE, Timestamp.valueOf(LocalDateTime.now()));
            roomToFlip.addRoomAssignment(roomAssignment);
            guestToFlip.addRoomAssignment(roomAssignment);
            Transaction tx1 = session.beginTransaction();
            roomAssignmentDao.save(session, roomAssignment);
            if (roomToFlip.getNumberOfBeds().equals(
                    roomAssignmentDao.getActiveRoomAssignmentsByRoomId(session,
                            roomToFlip.getId()).size())) {
                roomToFlip.setRoomStatus(RoomStatus.OCCUPIED);
            }
            guestDao.update(session, guestToFlip);
            roomDao.update(session, roomToFlip);
            tx1.commit();
            session.close();
        } else {
            log.log(Level.SEVERE, "Failed to accommodate guest to room");
            throw new OperationCancelledException("Unfortunately, this room is " +
                    roomToFlip.getRoomStatus().toString().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public void evictFromRoom(Long guestId) {
        Session session = hibernateSessionFactory.openSession();
        Guest guestToEvict = guestDao.getById(session, guestId);
        if (guestToEvict == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to evict guest from room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            guestToEvict.getRoomAssignments().stream()
                    .filter(a -> RoomAssignmentStatus.ACTIVE.equals(a.getRoomAssignmentStatus()))
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        Room roomToEvictFrom = roomDao.getById(session, roomAssignment.getRoom().getId());
                        roomToEvictFrom.setRoomStatus(RoomStatus.FREE);
                        roomAssignment.setRoomAssignmentStatus(RoomAssignmentStatus.CLOSED);
                        roomAssignment.setCheckOutDate(Timestamp.valueOf(LocalDateTime.now()));
                        Transaction tx1 = session.beginTransaction();
                        roomAssignmentDao.update(session, roomAssignment);
                        tx1.commit();
                        session.close();
                    },
                    () -> {
                        log.log(Level.SEVERE, "Failed to evict guest from room");
                        throw new OperationCancelledException("Guest does not live in any of the rooms");
                    });
        }
    }

    @Override
    public void orderMaintenance(Long guestId, Long maintenanceId) {
        Session session = hibernateSessionFactory.openSession();
        Guest guestToOrderMaintenance = guestDao.getById(session, guestId);
        Maintenance maintenanceToOrder = maintenanceDao.getById(session, maintenanceId);
        if (guestToOrderMaintenance == null || maintenanceToOrder == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to order maintenance");
            throw new IllegalArgumentException("Guest or maintenance not found");
        } else {
            roomAssignmentDao.getActiveRoomAssignmentsByGuestId(session,
                    guestToOrderMaintenance.getId()).stream()
                    .findAny().ifPresentOrElse(roomAssignment -> {
                        OrderedMaintenance orderedMaintenance = new OrderedMaintenance(roomAssignment, maintenanceToOrder, Timestamp.valueOf(LocalDateTime.now()));
                        roomAssignment.addMaintenance(orderedMaintenance);
                        orderedMaintenanceDao.save(session, orderedMaintenance);
                        Transaction tx1 = session.beginTransaction();
                        roomAssignmentDao.update(session, roomAssignment);
                        tx1.commit();
                        session.close();
                    },
                    () -> {
                        log.log(Level.SEVERE, "Failed to order maintenance");
                        throw new OperationCancelledException("Guest does not live in the hotel");
                    });
        }
    }

    @Override
    public Double getAmountOfPaymentForTheRoom(Long guestId) {
        Session session = hibernateSessionFactory.openSession();
        Guest guestToGetAmount = guestDao.getById(session, guestId);
        if (guestToGetAmount == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to get amount of payment for room");
            throw new IllegalArgumentException("Guest not found");
        } else {
            Double amountOfPaymentForTheRoom = 0.0;
            if (!roomAssignmentDao.getActiveRoomAssignmentsByGuestId(session,
                    guestToGetAmount.getId()).isEmpty()) {
                for (RoomAssignment roomAssignment : roomAssignmentDao.getActiveRoomAssignmentsByGuestId(session,
                        guestToGetAmount.getId())) {
                    amountOfPaymentForTheRoom = roomAssignmentService.getPricePerStay(roomAssignment);
                }
            }
            return amountOfPaymentForTheRoom;
        }
    }

    @Override
    public List<Guest> getAllGuests() {
        Session session = hibernateSessionFactory.openSession();
        return guestDao.getAll(session);
    }

    @Override
    public Integer getNumberOfGuests() {
        Session session = hibernateSessionFactory.openSession();
        int totalNumberOfGuest = 0;
        for (Guest guest : getAllGuests()) {
            if (!roomAssignmentDao.getActiveRoomAssignmentsByGuestId(session,
                    guest.getId()).isEmpty()) {
                totalNumberOfGuest++;
            }
        }
        return totalNumberOfGuest;
    }

    @Override
    public List<Guest> getThreeLastGuests(Long roomId) {
        Session session = hibernateSessionFactory.openSession();
        return guestDao.getThreeLastGuests(session, roomId);
    }

    @Override
    public List<Guest> sortGuestsABC() {
        Session session = hibernateSessionFactory.openSession();
        return guestDao.getSortedABCEntities(session);
    }
}
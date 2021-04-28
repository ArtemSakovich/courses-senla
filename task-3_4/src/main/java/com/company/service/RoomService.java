package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.configuration.annotation.ConfigClass;
import com.company.configuration.annotation.ConfigProperty;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Room;
import com.company.model.RoomStatus;
import com.company.util.DatabaseConnector;
import com.company.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
@DependencyClass
public class RoomService implements IRoomService {

    @DependencyComponent
    private IRoomDao roomDao;
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "isRoomStatusChangeable")
    private boolean isRoomStatusChangeable;
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "allowedNumberOfNotes")
    private int allowedNumberOfNotes;
    @DependencyComponent
    private DatabaseConnector databaseConnector;
    @DependencyComponent
    private HibernateSessionFactory hibernateSessionFactory;

    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    private RoomService() {

    }

    @Override
    public Room addRoom(Integer numberOfBeds, Integer numberOfStars, Integer roomNumber, Double roomPrice) {
        Session session = hibernateSessionFactory.openSession();
        Room room = new Room(numberOfBeds, numberOfStars, roomNumber, roomPrice);
        room.setRoomStatus(RoomStatus.FREE);
        Transaction tx1 = session.beginTransaction();
        roomDao.save(session, room);
        tx1.commit();
        session.close();
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getAll(session);
    }

    @Override
    public Room getById(Long roomId) {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getById(session, roomId);
    }

    @Override
    public void changeRoomStatus(Long id, RoomStatus newRoomStatus) {
        Session session = hibernateSessionFactory.openSession();
        if (isRoomStatusChangeable) {
            Room roomToChangeStatus = roomDao.getById(session, id);
            if (roomToChangeStatus == null) {
                log.log(Level.SEVERE, "Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Room not found!");
            } else {
                roomToChangeStatus.setRoomStatus(newRoomStatus);
                Transaction tx1 = session.beginTransaction();
                roomDao.update(session, roomToChangeStatus);
                tx1.commit();
                session.close();
            }
        } else {
            log.log(Level.SEVERE, "Error when trying to change room status");
            throw new IllegalArgumentException("This function is not available at the moment");
        }
    }

    @Override
    public void changeRoomPrice(Long id, Double newRoomPrice) {
        Session session = hibernateSessionFactory.openSession();
        Room roomToChangePrice = roomDao.getById(session, id);
        if (roomToChangePrice == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to change room price");
            throw new IllegalArgumentException("Room not found!");
        } else {
            roomToChangePrice.setRoomPrice(newRoomPrice);
            Transaction tx1 = session.beginTransaction();
            roomDao.update(session, roomToChangePrice);
            tx1.commit();
            session.close();
        }
    }

    @Override
    public List<Room> getAllFreeRooms() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getFreeRooms(session);
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return getAllFreeRooms().size();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDateTime requiredDate) {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getFreeRoomsByDate(session, requiredDate);
    }

    @Override
    public List<Room> sortRoomsByPrice() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getRoomsSortedByPrice(session);
    }

    @Override
    public List<Room> sortRoomsByNumberOfBeds() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getRoomsSortedByNumberOfBeds(session);
    }

    @Override
    public List<Room> sortRoomsByNumberOfStars() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.getRoomsSortedByNumberOfStars(session);
    }

    @Override
    public List<Room> sortFreeRoomsByPrice() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.sortFreeRoomsByPrice(session);
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfBeds() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.sortFreeRoomsByNumberOfBeds(session);
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfStars() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.sortFreeRoomsByNumberOfStars(session);
    }

    @Override
    public List<Room> sortRoomsByCheckOutDate() {
        Session session = hibernateSessionFactory.openSession();
        return roomDao.sortRoomsByCheckOutDate(session);
    }
}
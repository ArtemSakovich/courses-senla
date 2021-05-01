package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;

import com.company.model.Room;
import com.company.model.RoomStatus;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomDao roomDao;
    @Value("${isRoomStatusChangeable:false}")
    private boolean isRoomStatusChangeable;
    @Value("${allowedNumberOfNotes:10}")
    private int allowedNumberOfNotes;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    private RoomService() {

    }

    @Override
    @Transactional
    public Room addRoom(Integer numberOfBeds, Integer numberOfStars, Integer roomNumber, Double roomPrice) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Room room = new Room(numberOfBeds, numberOfStars, roomNumber, roomPrice);
        room.setRoomStatus(RoomStatus.FREE);
        roomDao.save(session, room);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomDao.getAll(session);
    }

    @Override
    public Room getById(Long roomId) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomDao.getById(session, roomId);
    }

    @Override
    @Transactional
    public void changeRoomStatus(Long id, RoomStatus newRoomStatus) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        if (isRoomStatusChangeable) {
            Room roomToChangeStatus = roomDao.getById(session, id);
            if (roomToChangeStatus == null) {
                log.warn("Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Room not found!");
            } else {
                roomToChangeStatus.setRoomStatus(newRoomStatus);
                roomDao.update(session, roomToChangeStatus);
            }
        } else {
            log.warn("Error when trying to change room status");
            throw new IllegalArgumentException("This function is not available at the moment");
        }
    }

    @Override
    @Transactional
    public void changeRoomPrice(Long id, Double newRoomPrice) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Room roomToChangePrice = roomDao.getById(session, id);
        if (roomToChangePrice == null) {
            log.warn("Incorrect input when trying to change room price");
            throw new IllegalArgumentException("Room not found!");
        } else {
            roomToChangePrice.setRoomPrice(newRoomPrice);
            roomDao.update(session, roomToChangePrice);
        }
    }

    @Override
    public List<Room> getAllFreeRooms() {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomDao.getFreeRooms(session);
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return getAllFreeRooms().size();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDateTime requiredDate) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomDao.getFreeRoomsByDate(session, requiredDate);
    }

    @Override
    public List<Room> getSortedRooms(String paramToSort) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomDao.getSortedRooms(session, paramToSort);
    }

    @Override
    public List<Room> getFreeSortedRooms(String paramToSort) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return roomDao.getFreeSortedRooms(session, paramToSort);
    }
}
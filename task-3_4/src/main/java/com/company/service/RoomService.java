package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;

import com.company.model.Room;
import com.company.model.RoomStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    private RoomService() {

    }

    @Override
    @Transactional
    public Room addRoom(Integer numberOfBeds, Integer numberOfStars, Integer roomNumber, Double roomPrice) {
        Room room = new Room(numberOfBeds, numberOfStars, roomNumber, roomPrice);
        room.setRoomStatus(RoomStatus.FREE);
        roomDao.save(room);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAll();
    }

    @Override
    public Room getById(Long roomId) {
        return roomDao.getById(roomId);
    }

    @Override
    @Transactional
    public void changeRoomStatus(Long id, RoomStatus newRoomStatus) {
        if (isRoomStatusChangeable) {
            Room roomToChangeStatus = roomDao.getById(id);
            if (roomToChangeStatus == null) {
                log.warn("Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Room not found!");
            } else {
                roomToChangeStatus.setRoomStatus(newRoomStatus);
                roomDao.update(roomToChangeStatus);
            }
        } else {
            log.warn("Error when trying to change room status");
            throw new IllegalArgumentException("This function is not available at the moment");
        }
    }

    @Override
    @Transactional
    public void changeRoomPrice(Long id, Double newRoomPrice) {
        Room roomToChangePrice = roomDao.getById(id);
        if (roomToChangePrice == null) {
            log.warn("Incorrect input when trying to change room price");
            throw new IllegalArgumentException("Room not found!");
        } else {
            roomToChangePrice.setRoomPrice(newRoomPrice);
            roomDao.update(roomToChangePrice);
        }
    }

    @Override
    public List<Room> getAllFreeRooms() {
        return roomDao.getFreeRooms();
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
    public List<Room> getSortedRooms(String paramToSort) {
        return roomDao.getSortedRooms(paramToSort);
    }

    @Override
    public List<Room> getFreeSortedRooms(String paramToSort) {
        return roomDao.getFreeSortedRooms(paramToSort);
    }
}
package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.model.Room;
import com.company.model.RoomStatus;
import com.company.util.IdGenerator;

public class RoomService implements IRoomService {
    private final IRoomDao roomDao;

    public RoomService(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds) {
        Room room = new Room(roomNumber, roomPrice, numberOfBeds);
        room.setId(IdGenerator.generateRoomId());
        room.setRoomStatus(RoomStatus.FREE);
        roomDao.save(room);
        return room;
    }

    @Override
    public void update(Room updatedRoom) {
        roomDao.update(updatedRoom);
    }

    @Override
    public Room getById(Long id) {
        return roomDao.getById(id);
    }

    @Override
    public void changeRoomStatus(Room room, RoomStatus newRoomStatus) {
        room.setRoomStatus(newRoomStatus);
    }

    @Override
    public void changeRoomPrice(Room room, Double newRoomPrice) {
        room.setRoomPrice(newRoomPrice);
    }
}

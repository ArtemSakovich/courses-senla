package com.company.dao;

import com.company.api.dao.IRoomDao;
import com.company.model.Room;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {
    @Override
    public void update(Room updatedRoom) {
        Room room = getById(updatedRoom.getId());
        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomPrice(updatedRoom.getRoomPrice());
        room.setRoomStatus(updatedRoom.getRoomStatus());
        room.setNumberOfBeds(updatedRoom.getNumberOfBeds());
    }
}
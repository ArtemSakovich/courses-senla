package com.company.dao;

import com.company.api.dao.IGenericDao;
import com.company.api.dao.IRoomDao;
import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomStatus;

import java.util.*;

public class RoomDao extends AbstractDao<Room> implements IRoomDao {
    @Override
    public void update(Room updatedRoom) {
        Room room = getById(updatedRoom.getId());
        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomPrice(updatedRoom.getRoomPrice());
        room.setRoomStatus(updatedRoom.getRoomStatus());
        room.setNumberOfBeds(updatedRoom.getNumberOfBeds());
        room.setTenants(updatedRoom.getTenants());
    }

    /*public void flipToRoom(Guest guest, Room room) {
        guest.setRoom(room);
        List<Guest> guestToFlip = new ArrayList<>();
        guestToFlip.add(guest);
        room.setTenants(guestToFlip);
    }

    public void evictFromRoom(Guest guest) {
        List<Guest> guestsFromRoomForEviction = new ArrayList<>(guest.getRoom().getTenants());
        guestsFromRoomForEviction.remove(guest);
        guest.getRoom().setTenants(guestsFromRoomForEviction);
        guest.setRoom(null);
    }*/

}
package com.company.api.service;

import com.company.model.Room;
import com.company.model.RoomStatus;

public interface IRoomService {
    public Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds);

    void update(Room updatedRoom);

    Room getById(Long id);

    void changeRoomStatus(Room room, RoomStatus newRoomStatus);

    void changeRoomPrice(Room room, Double newRoomPrice);
}

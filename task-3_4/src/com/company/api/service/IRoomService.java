package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomAssignment;
import com.company.model.RoomStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IRoomService {
    Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars);

    void update(Room updatedRoom);

    Room getById(Long id);

    void changeRoomStatus(Long id, RoomStatus newRoomStatus);

    void changeRoomPrice(Long id, Double newRoomPrice);

    List<Room> getAllRooms();

    List<Room> getAllFreeRooms();

    Integer getNumberOfFreeRooms();

    List<Room> getFreeRoomsByDate(LocalDateTime requiredDate);

    List<Room> sortRoomsByPrice();

    List<Room> sortRoomsByNumberOfBeds();

    List<Room> sortRoomsByNumberOfStars();

    List<Room> sortFreeRoomsByPrice();

    List<Room> sortFreeRoomsByNumberOfBeds();

    List<Room> sortFreeRoomsByNumberOfStars();

    List<RoomAssignment> getThreeLastRoomAssigment(Long roomId);

    List<Guest> getThreeLastGuests(Long roomId);

    List<String> getThreeLastGuestsCheckInDates(Long roomId);
}
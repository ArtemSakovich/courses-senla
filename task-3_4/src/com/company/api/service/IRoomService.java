package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomAssignment;
import com.company.model.RoomStatus;

import java.time.LocalDate;
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

    List<Room> getFreeRoomsByDate(LocalDate requiredDate);

    public List<Room> sortRoomsByPrice();

    public List<Room> sortRoomsByNumberOfBeds();

    public List<Room> sortRoomsByNumberOfStars();

    public List<Room> sortFreeRoomsByPrice();

    public List<Room> sortFreeRoomsByNumberOfBeds();

    public List<Room> sortFreeRoomsByNumberOfStars();

    public List<RoomAssignment> getThreeLastRoomAssigment(Long roomId);

    public List<Guest> getThreeLastGuests(Long roomId);

    public List<String> getThreeLastGuestsCheckInDates(Long roomId);
}
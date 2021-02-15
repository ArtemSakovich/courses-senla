package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomAssignment;
import com.company.model.RoomStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public interface IRoomService {
    Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars);

    void update(Room updatedRoom);

    Room getById(Long id);

    void changeRoomStatus(Room room, RoomStatus newRoomStatus);

    void changeRoomPrice(Room room, Double newRoomPrice);

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

    public List<Room> sortRoomsByCheckOutDate();

    public List<RoomAssignment> getThreeLastRoomAssigment(Room room);

    public List<Guest> threeLastGuests(Room room);

    public List<LocalDate> threeLastGuestsCheckInDates(Room room);
}
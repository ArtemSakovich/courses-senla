package com.company.api.service;

import com.company.model.Room;
import com.company.model.RoomStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IRoomService {

    Room addRoom(Integer numberOfBeds, Integer numberOfStars, Integer roomNumber, Double roomPrice);

    List<Room> getAllRooms();

    Room getById(Long roomId);

    void changeRoomStatus(Long id, RoomStatus newRoomStatus);

    void changeRoomPrice(Long id, Double newRoomPrice);

    List<Room> getAllFreeRooms();

    Integer getNumberOfFreeRooms();

    List<Room> getFreeRoomsByDate(LocalDateTime requiredDate);

    List<Room> sortRoomsByPrice();

    List<Room> sortRoomsByNumberOfBeds();

    List<Room> sortRoomsByNumberOfStars();

    List<Room> sortFreeRoomsByPrice();

    List<Room> sortFreeRoomsByNumberOfBeds();

    List<Room> sortFreeRoomsByNumberOfStars();

    List<Room> sortRoomsByCheckOutDate();
}
package com.company.api.dao;

import com.company.model.Room;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public interface IRoomDao extends IGenericDao<Room>{

    List<Room> sortRoomsByCheckOutDate(Connection connection);

    List<Room> getFreeRooms(Connection connection);

    List<Room> getFreeRoomsByDate(Connection connection, LocalDateTime requiredDate);

    List<Room> getRoomsSortedByPrice(Connection connection);

    List<Room> getRoomsSortedByNumberOfBeds(Connection connection);

    List<Room> getRoomsSortedByNumberOfStars(Connection connection);

    List<Room> sortFreeRoomsByPrice(Connection connection);

    List<Room> sortFreeRoomsByNumberOfBeds(Connection connection);

    List<Room> sortFreeRoomsByNumberOfStars(Connection connection);
}

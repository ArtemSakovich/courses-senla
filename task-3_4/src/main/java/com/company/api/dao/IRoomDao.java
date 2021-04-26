package com.company.api.dao;

import com.company.model.Room;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public interface IRoomDao extends IGenericDao<Room>{

    List<Room> sortRoomsByCheckOutDate(EntityManager entityManager);

    List<Room> getFreeRooms(EntityManager entityManager);

    List<Room> getFreeRoomsByDate(EntityManager entityManager, LocalDateTime requiredDate);

    List<Room> getRoomsSortedByPrice(EntityManager entityManager);

    List<Room> getRoomsSortedByNumberOfBeds(EntityManager entityManager);

    List<Room> getRoomsSortedByNumberOfStars(EntityManager entityManager);

    List<Room> sortFreeRoomsByPrice(EntityManager entityManager);

    List<Room> sortFreeRoomsByNumberOfBeds(EntityManager entityManager);

    List<Room> sortFreeRoomsByNumberOfStars(EntityManager entityManager);
}

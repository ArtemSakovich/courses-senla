package com.company.api.dao;

import com.company.model.Room;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public interface IRoomDao extends IGenericDao<Room>{

    List<Room> getFreeRooms(EntityManager entityManager);

    List<Room> getFreeRoomsByDate(EntityManager entityManager, LocalDateTime requiredDate);

    List<Room> getSortedRooms(EntityManager entityManager, String paramToSort);

    List<Room> getFreeSortedRooms(EntityManager entityManager, String paramToSort);
}

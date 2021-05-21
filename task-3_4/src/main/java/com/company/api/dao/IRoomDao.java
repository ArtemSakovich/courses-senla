package com.company.api.dao;

import com.company.model.Room;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public interface IRoomDao extends IGenericDao<Room>{

    List<Room> getFreeRooms();

    List<Room> getFreeRoomsByDate(LocalDateTime requiredDate);

    List<Room> getFreeSortedRooms(String paramToSort);
}

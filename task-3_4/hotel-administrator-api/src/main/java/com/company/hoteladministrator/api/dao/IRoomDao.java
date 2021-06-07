package com.company.hoteladministrator.api.dao;

import com.company.hoteladministrator.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface IRoomDao extends IGenericDao<Room>{

    List<Room> getFreeRooms();

    List<Room> getFreeRoomsByDate(LocalDateTime requiredDate);

    List<Room> getFreeSortedRooms(String paramToSort);

    Room getRoomByGuestId(Long guestId);
}

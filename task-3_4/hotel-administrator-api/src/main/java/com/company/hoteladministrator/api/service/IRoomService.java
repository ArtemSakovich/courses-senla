package com.company.hoteladministrator.api.service;


import com.company.hoteladministrator.model.Room;
import com.company.hoteladministrator.model.dto.RoomDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IRoomService {

    RoomDto addRoom(RoomDto roomDto);

    List<RoomDto> getAllRooms();

    RoomDto changeRoomInfo(RoomDto roomDto);

    List<RoomDto> getAllFreeRooms();

    Integer getNumberOfFreeRooms();

    List<Room> getFreeRoomsByDate(LocalDateTime requiredDate);

    RoomDto getRoomById(Long id);

    List<RoomDto> getSortedRooms(String paramToSort);

    List<RoomDto> getFreeSortedRooms(String paramToSort);

    RoomDto getRoomByGuestId(Long guestId);

    void deleteRoom(Long roomId);
}
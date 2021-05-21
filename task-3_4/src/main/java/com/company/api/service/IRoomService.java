package com.company.api.service;

import com.company.dto.RoomDto;
import com.company.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface IRoomService {

    RoomDto addRoom(RoomDto roomDto);

    List<RoomDto> getAllRooms();

    RoomDto changeRoomInfo(RoomDto roomDto);

    List<RoomDto> getAllFreeRooms();

    Integer getNumberOfFreeRooms();

    List<Room> getFreeRoomsByDate(LocalDateTime requiredDate);

    List<RoomDto> getSortedRooms(String paramToSort);

    List<RoomDto> getFreeSortedRooms(String paramToSort);

    void deleteRoom(Long roomId);
}
package com.company.controller;

import com.company.api.service.IRoomService;
import com.company.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    IRoomService roomService;

    @Autowired
    private RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/room")
    @ResponseBody
    public RoomDto addRoom(@RequestBody RoomDto roomDto) {
        return roomService.addRoom(roomDto);
    }

    @GetMapping(value = "/rooms")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(value = "/free-rooms")
    public List<RoomDto> getAllFreeRooms() {
        return roomService.getAllFreeRooms();
    }

    @GetMapping(value = "/number-of-free-rooms")
    public Integer getNumberOfFreeRooms() {
        return roomService.getNumberOfFreeRooms();
    }

    @GetMapping(value = "/sorted-rooms/{paramToSort}")
    public List<RoomDto> getSortedRooms(@PathVariable String paramToSort) {
        return roomService.getSortedRooms(paramToSort);
    }

    @PutMapping(value = "/room")
    @ResponseBody
    public RoomDto changeRoomInfo(@RequestBody RoomDto roomDto) {
        return roomService.changeRoomInfo(roomDto);
    }

    @GetMapping(value = "/free-sorted-rooms/{paramToSort}")
    public List<RoomDto> getFreeSortedRooms(@PathVariable String paramToSort) {
        return roomService.getFreeSortedRooms(paramToSort);
    }

    @DeleteMapping(value = "/room/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
    }
}
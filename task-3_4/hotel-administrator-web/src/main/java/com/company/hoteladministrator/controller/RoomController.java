package com.company.hoteladministrator.controller;

import com.company.hoteladministrator.api.service.IRoomService;
import com.company.hoteladministrator.model.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RoomController {

    private final IRoomService roomService;

    @Autowired
    private RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/admin/room")
    @ResponseBody
    public RoomDto addRoom(@RequestBody RoomDto roomDto) {
        return roomService.addRoom(roomDto);
    }

    @GetMapping(value = "/user/rooms")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(value = "/user/free-rooms")
    public List<RoomDto> getAllFreeRooms() {
        return roomService.getAllFreeRooms();
    }

    @GetMapping(value = "/user/number-of-free-rooms")
    public Integer getNumberOfFreeRooms() {
        return roomService.getNumberOfFreeRooms();
    }

    @GetMapping(value = "/user/sorted-rooms/{paramToSort}")
    public List<RoomDto> getSortedRooms(@PathVariable String paramToSort) {
        return roomService.getSortedRooms(paramToSort);
    }

    @PutMapping(value = "/admin/room")
    @ResponseBody
    public RoomDto changeRoomInfo(@RequestBody RoomDto roomDto) {
        return roomService.changeRoomInfo(roomDto);
    }

    @GetMapping(value = "/user/free-sorted-rooms/{paramToSort}")
    public List<RoomDto> getFreeSortedRooms(@PathVariable String paramToSort) {
        return roomService.getFreeSortedRooms(paramToSort);
    }

    @GetMapping(value = "/user/rooms/{roomId}")
    public RoomDto getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @GetMapping(value = "/user/room/{guestId}")
    public RoomDto getRoomByGuestId(@PathVariable Long guestId) {
        return roomService.getRoomById(guestId);
    }

    @DeleteMapping(value = "/admin/room/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
    }
}
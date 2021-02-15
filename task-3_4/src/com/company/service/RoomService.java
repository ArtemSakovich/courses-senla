package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.model.*;
import com.company.util.IdGenerator;
import com.company.util.comparators.roomComparators.CheckOutDateComparator;
import com.company.util.comparators.roomComparators.NumberOfBedsComparator;
import com.company.util.comparators.roomComparators.NumberOfStarsComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomService implements IRoomService {
    private final IRoomDao roomDao;

    public RoomService(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars) {
        Room room = new Room(roomNumber, roomPrice, numberOfBeds, numberOfStars);
        room.setId(IdGenerator.generateRoomId());
        room.setRoomStatus(RoomStatus.FREE);
        roomDao.save(room);
        return room;
    }

    @Override
    public void update(Room updatedRoom) {
        roomDao.update(updatedRoom);
    }

    @Override
    public Room getById(Long id) {
        return roomDao.getById(id);
    }

    @Override
    public void changeRoomStatus(Room room, RoomStatus newRoomStatus) {
        room.setRoomStatus(newRoomStatus);
    }

    @Override
    public void changeRoomPrice(Room room, Double newRoomPrice) {
        room.setRoomPrice(newRoomPrice);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAll();
    }


    @Override
    public List<Room> getAllFreeRooms() {
        List<Room> freeRooms = new ArrayList<>();
        for (Room room : getAllRooms()) {
            if (room.getRoomStatus().equals(RoomStatus.FREE)) {
                freeRooms.add(room);
            }
        }
        return freeRooms;
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return getAllFreeRooms().size();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDate requiredDate) {
        List<Room> freeRooms = new ArrayList<>();
        for (Room room : getAllRooms()) {
            for (RoomAssignment roomAssignment : room.getRoomAssignments()) {
                if (roomAssignment.getRoomAssignmentStatus().equals(RoomAssignmentStatus.ACTIVE) ||
                        roomAssignment.getRoomAssignmentStatus().equals(RoomAssignmentStatus.BOOKED))
                    if (requiredDate.isAfter(roomAssignment.getCheckOutDate())) {
                        freeRooms.add(room);
                    }
            }
        }
        return freeRooms;
    }

    @Override
    public List<Room> sortRoomsByPrice() {
        List<Room> roomsToSort = new ArrayList<>(getAllRooms());
        Collections.sort(roomsToSort);
        return roomsToSort;
    }

    @Override
    public List<Room> sortRoomsByNumberOfBeds() {
        List<Room> roomsToSort = new ArrayList<>(getAllRooms());
        Collections.sort(roomsToSort, new NumberOfBedsComparator());
        return roomsToSort;
    }

    @Override
    public List<Room> sortRoomsByNumberOfStars() {
        List<Room> roomsToSort = new ArrayList<>(getAllRooms());
        Collections.sort(roomsToSort, new NumberOfStarsComparator());
        return roomsToSort;
    }

    @Override
    public List<Room> sortFreeRoomsByPrice() {
        List<Room> roomsToSort = new ArrayList<>(getAllFreeRooms());
        Collections.sort(roomsToSort);
        return roomsToSort;
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfBeds() {
        List<Room> roomsToSort = new ArrayList<>(getAllFreeRooms());
        Collections.sort(roomsToSort, new NumberOfBedsComparator());
        return roomsToSort;
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfStars() {
        List<Room> roomsToSort = new ArrayList<>(getAllFreeRooms());
        Collections.sort(roomsToSort, new NumberOfStarsComparator());
        return roomsToSort;
    }

    @Override
    public List<Room> sortRoomsByCheckOutDate() {
        List<Room> roomsToSort = new ArrayList<>(getAllFreeRooms());
        Collections.sort(roomsToSort, new CheckOutDateComparator());
        return roomsToSort;
    }

    @Override
    public List<RoomAssignment> getThreeLastRoomAssigment(Room room) {
        List<RoomAssignment> allAssignments = new ArrayList<RoomAssignment>(room.getRoomAssignments());
        List<RoomAssignment> threeLastRoomAssignments = new ArrayList<>();

        Collections.reverse(allAssignments);

        for (int i = 0; i < 3; i++) {
            threeLastRoomAssignments.add(allAssignments.get(i));
        }
        return threeLastRoomAssignments;
    }

    @Override
    public List<Guest> threeLastGuests(Room room) {
        List<Guest> threeLastGuests = new ArrayList<>();
        for (RoomAssignment roomAssignment : getThreeLastRoomAssigment(room)) {
            threeLastGuests.add(roomAssignment.getGuest());
        }
        return threeLastGuests;
    }

    @Override
    public List<LocalDate> threeLastGuestsCheckInDates(Room room) {
        List<LocalDate> threeLastGuestsCheckInDates = new ArrayList<>();
        for (RoomAssignment roomAssignment : getThreeLastRoomAssigment(room)) {
            threeLastGuestsCheckInDates.add(roomAssignment.getCheckInDate());
        }
        return threeLastGuestsCheckInDates;
    }
}
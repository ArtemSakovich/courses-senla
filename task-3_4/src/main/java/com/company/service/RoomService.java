package com.company.service;

import com.company.api.dao.IRoomDao;
import com.company.api.service.IRoomService;
import com.company.configuration.annotation.ConfigClass;
import com.company.configuration.annotation.ConfigProperty;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomAssignment;
import com.company.model.RoomStatus;
import com.company.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ConfigClass
@DependencyClass
public class RoomService implements IRoomService {

    @DependencyComponent
    private IRoomDao roomDao;
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "isRoomStatusChangeable")
    private boolean isRoomStatusChangeable;
    @ConfigProperty(configName = "hoteladministrator.properties", propertyName = "allowedNumberOfNotes")
    private int allowedNumberOfNotes;
    private static final Logger log = Logger.getLogger(RoomService.class.getName());

    private RoomService() {

    }

    @Override
    public Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars) {
        Room room = new Room(roomNumber, roomPrice, numberOfBeds, numberOfStars);
        room.setId(IdGenerator.getInstance().generateId(roomDao.getMaxId()));
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
    public void changeRoomStatus(Long id, RoomStatus newRoomStatus) {
        if (isRoomStatusChangeable) {
            Room roomToChangeStatus = roomDao.getById(id);
            if (roomToChangeStatus == null) {
                log.log(Level.SEVERE, "Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Room not found!");
            } else {
                roomToChangeStatus.setRoomStatus(newRoomStatus);
            }
        } else {
            log.log(Level.SEVERE, "Error when trying to change room status");
            throw new IllegalArgumentException("This function is not available at the moment");
        }
    }

    @Override
    public void changeRoomPrice(Long id, Double newRoomPrice) {
        Room roomToChangePrice = roomDao.getById(id);
        if (roomToChangePrice == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to change room price");
            throw new IllegalArgumentException("Room not found!");
        } else {
            roomToChangePrice.setRoomPrice(newRoomPrice);
        }
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAll();
    }

    @Override
    public List<Room> getAllFreeRooms() {
        return getAllRooms().stream()
                .filter(Room::isFree)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return getAllFreeRooms().size();
    }

    @Override
    public List<Room> getFreeRoomsByDate(LocalDateTime requiredDate) {
        List<Room> freeRooms = new ArrayList<>();
        for (Room room : getAllRooms()) {
            if (room.getRoomAssignments().isEmpty()) {
                freeRooms.add(room);
            } else {
                for (RoomAssignment roomAssignment : room.getRoomAssignments()) {
                    if (requiredDate.isAfter(roomAssignment.getCheckOutDate())) {
                        freeRooms.add(room);
                    }
                }
            }
        }
        return freeRooms.stream().distinct().collect(Collectors.toList());
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
        roomsToSort.sort(Comparator.comparingInt(Room::getNumberOfBeds));
        return roomsToSort;
    }

    @Override
    public List<Room> sortRoomsByNumberOfStars() {
        List<Room> roomsToSort = new ArrayList<>(getAllRooms());
        roomsToSort.sort(Comparator.comparingInt(Room::getNumberOfStars));
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
        roomsToSort.sort(Comparator.comparingInt(Room::getNumberOfBeds));
        return roomsToSort;
    }

    @Override
    public List<Room> sortFreeRoomsByNumberOfStars() {
        List<Room> roomsToSort = new ArrayList<>(getAllFreeRooms());
        roomsToSort.sort(Comparator.comparingInt(Room::getNumberOfStars));
        return roomsToSort;
    }

    @Override
    public List<RoomAssignment> getThreeLastRoomAssigment(Long roomId) {
        return roomDao.getById(roomId).getRoomAssignments().stream()
                .sorted(Comparator.comparing(RoomAssignment::getCreatedOn).reversed())
                .limit(allowedNumberOfNotes).collect(Collectors.toList());
    }

    @Override
    public List<Guest> getThreeLastGuests(Long roomId) {
        Room roomToGetGuests = roomDao.getById(roomId);
        if (roomToGetGuests == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to get three last guests");
            throw new IllegalArgumentException("Room not found");
        } else {
            List<Guest> threeLastGuests = new ArrayList<>();
            for (RoomAssignment roomAssignment : getThreeLastRoomAssigment(roomToGetGuests.getId())) {
                threeLastGuests.add(roomAssignment.getGuest());
            }
            return threeLastGuests;
        }
    }


    @Override
    public List<String> getThreeLastGuestsCheckInDates(Long roomId) {
        Room roomToGetGuests = roomDao.getById(roomId);
        if (roomToGetGuests == null) {
            log.log(Level.SEVERE, "Incorrect input when trying to get three last dates of staying");
            throw new IllegalArgumentException("Room not found");
        } else {
            List<String> threeLastGuestsCheckInDates = new ArrayList<>();
            for (RoomAssignment roomAssignment : getThreeLastRoomAssigment(roomToGetGuests.getId())) {
                threeLastGuestsCheckInDates.add("From: " + roomAssignment.getCheckInDate().toLocalDate()
                        + " To: " + roomAssignment.getCheckOutDate().toLocalDate());
            }
            return threeLastGuestsCheckInDates;
        }
    }
}
package com.company.facade;

import com.company.api.service.IGuestService;
import com.company.api.service.IMaintenanceService;
import com.company.api.service.IRoomAssignmentService;
import com.company.api.service.IRoomService;
import com.company.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Facade {
    @Autowired
    private IGuestService guestService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IMaintenanceService maintenanceService;
    @Autowired
    private IRoomAssignmentService roomAssignmentService;

    private Facade() {
    }

    public Guest addGuest(String name, String surname, Integer age) {
        return guestService.addGuest(name, surname, age);
    }

    public Maintenance addMaintenance(String name, Double price, MaintenanceSection maintenanceSection) {
        return maintenanceService.addMaintenance(name, price, maintenanceSection);
    }

    public Room addRoom(Integer numberOfBeds, Integer numberOfStars, Integer roomNumber, Double roomPrice) {
        return roomService.addRoom(numberOfBeds, numberOfStars, roomNumber, roomPrice);
    }

    public void evictFromRoom(Long guestId) {
        guestService.evictFromRoom(guestId);
    }

    public void accommodateToRoom(Long guestId, Long roomId, LocalDateTime checkOutDate) {
        guestService.accommodateToRoom(guestId, roomId, checkOutDate);
    }

    public void changeMaintenancePrice(Long maintenanceId, Double newMaintenancePrice) {
        maintenanceService.changeMaintenancePrice(maintenanceId, newMaintenancePrice);
    }

    public void changeRoomPrice(Long roomId, Double newRoomPrice) {
        roomService.changeRoomPrice(roomId, newRoomPrice);
    }

    public void changeRoomStatus(Long roomId, RoomStatus roomStatus) {
        roomService.changeRoomStatus(roomId, roomStatus);
    }

    public void viewListOfRooms() {
        roomService.getAllRooms().forEach(System.out::println);
    }

    public void sortRoomsByPrice() {
        roomService.getSortedRooms("roomPrice").forEach(System.out::println);
    }

    public void sortRoomsByCapacity() { roomService.getSortedRooms("numberOfBeds").forEach(System.out::println);}

    public void sortRoomsByNumberOfStars() {
        roomService.getSortedRooms("numberOfStars").forEach(System.out::println);
    }

    public void viewListOfFreeRooms() {
        roomService.getAllFreeRooms().forEach(System.out::println);
    }

    public void sortFreeRoomsByPrice() {
        roomService.getFreeSortedRooms("roomPrice").forEach(System.out::println);
    }

    public void sortFreeRoomsByCapacity() {
        roomService.getFreeSortedRooms("numberOfBeds").forEach(System.out::println);
    }

    public void sortFreeRoomsByNumberOfStars() {
        roomService.getFreeSortedRooms("numberOfStars").forEach(System.out::println);
    }

    public void viewListOfGuests() {
        guestService.getAllGuests().forEach(System.out::println);
    }

    public void sortGuestsABC() {
        guestService.getSortedGuests("name").forEach(System.out::println);
    }

    public void sortRoomsByCheckOutDate() {
        if (roomService.getSortedRooms("checkOutDate").isEmpty()) {
            System.out.println("All rooms are free");
        } else {
            roomService.getSortedRooms("checkOutDate").forEach(System.out::println);
        }
    }

    public void getNumberOfFreeRooms() {
        System.out.println(roomService.getNumberOfFreeRooms());
    }

    public void getNumberOfGuests()  {
        System.out.println(guestService.getNumberOfGuests());
    }

    public void getFreeRoomsByDate(LocalDateTime dateToCheck) {
        roomService.getFreeRoomsByDate(dateToCheck).forEach(System.out::println);
    }

    public Double getAmountPerStay(Long guestId) {
       return guestService.getAmountOfPaymentForTheRoom(guestId);
    }

    public void getThreeLastGuests(Long roomId) {
        guestService.getThreeLastGuests(roomId).forEach(System.out::println);
        roomAssignmentService.getThreeLastRoomAssigmentDates(roomId).forEach(System.out::println);
    }

    public void viewListOfMaintenancesOfCertainGuest(Long guestId) {
        maintenanceService.getAllMaintenancesOfCertainGuest(guestId).forEach(System.out::println);
    }

    public void sortMaintenancesOfCertainGuestByPrice(Long guestId) {
        maintenanceService.getSortedMaintenancesOfCertainGuest(guestId, "maintenancePrice").forEach(System.out::println);
    }

    public void sortMaintenancesOfCertainGuestByOrderDate(Long guestId) {
        maintenanceService.getSortedMaintenancesOfCertainGuest(guestId, "orderDate").forEach(System.out::println);
    }

    public void sortAllMaintenancesBySectionABC() {
        maintenanceService.getSortedMaintenances("maintenanceSection").forEach(System.out::println);
    }

    public void sortAllMaintenancesByPrice() {
        maintenanceService.getSortedMaintenances("maintenancePrice").forEach(System.out::println);
    }

    public Room viewRoomDetails(Long roomId) {
       return roomService.getById(roomId);
    }

    public void orderMaintenance(Long guestId, Long maintenanceIdId) {
        guestService.orderMaintenance(guestId, maintenanceIdId);
    }

    public void getAllOrderedMaintenances() {
        maintenanceService.getAllOrderedMaintenances().forEach(System.out::println);
    }
}

package com.company.facade;

import com.company.api.service.IGuestService;
import com.company.api.service.IMaintenanceService;
import com.company.api.service.IRoomAssignmentService;
import com.company.api.service.IRoomService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.*;

import java.time.LocalDateTime;
import java.util.List;

@DependencyClass
public class Facade {
    @DependencyComponent
    private IGuestService guestService;
    @DependencyComponent
    private IRoomService roomService;
    @DependencyComponent
    private IMaintenanceService maintenanceService;
    @DependencyComponent
    private IRoomAssignmentService roomAssignmentService;

    private Facade() {
    }

    public Guest addGuest(String name, String surname, Integer age) {
        return guestService.addGuest(name, surname, age);
    }

    public Maintenance addMaintenance(String name, Double price, MaintenanceSection maintenanceSection) {
        return maintenanceService.addMaintenance(name, price, maintenanceSection);
    }

    public Room addRoom(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars) {
        return roomService.addRoom(roomNumber, roomPrice, numberOfBeds, numberOfStars);
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
        roomService.sortRoomsByPrice().forEach(System.out::println);
    }

    public void sortRoomsByCapacity() {
        roomService.sortRoomsByNumberOfBeds().forEach(System.out::println);
    }

    public void sortRoomsByNumberOfStars() {
        roomService.sortRoomsByNumberOfStars().forEach(System.out::println);
    }

    public void viewListOfFreeRooms() {
        roomService.getAllFreeRooms().forEach(System.out::println);
    }

    public void sortFreeRoomsByPrice() {
        roomService.sortFreeRoomsByPrice().forEach(System.out::println);
    }

    public void sortFreeRoomsByCapacity() {
        roomService.sortFreeRoomsByNumberOfBeds().forEach(System.out::println);
    }

    public void sortFreeRoomsByNumberOfStars() {
        roomService.sortFreeRoomsByNumberOfStars().forEach(System.out::println);
    }

    public void viewListOfGuests() {
        guestService.getAllGuests().forEach(System.out::println);
    }

    public void sortGuestsABC() {
        guestService.sortGuestsABC().forEach(System.out::println);
    }

    public void sortRoomsByCheckOutDate() {
        if (roomService.sortRoomsByCheckOutDate().isEmpty()) {
            System.out.println("All rooms are free");
        } else {
            roomService.sortRoomsByCheckOutDate().forEach(System.out::println);
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
        maintenanceService.sortMaintenancesOfCertainGuestByPrice(guestId).forEach(System.out::println);
    }

    public void sortMaintenancesOfCertainGuestByOrderDate(Long guestId) {
        maintenanceService.sortMaintenancesByOrderDate(guestId).forEach(System.out::println);
    }

    public void sortAllMaintenancesBySectionABC() {
        maintenanceService.sortAllMaintenancesBySectionABC().forEach(System.out::println);
    }

    public void sortAllMaintenancesByPrice() {
        maintenanceService.sortAllMaintenancesByPrice().forEach(System.out::println);
    }

    public Room viewRoomDetails(Long roomId) {
       return roomService.getById(roomId);
    }

    public void orderMaintenance(Long guestId, Long maintenanceIdId) {
        guestService.orderMaintenance(guestId, maintenanceIdId);
    }
}

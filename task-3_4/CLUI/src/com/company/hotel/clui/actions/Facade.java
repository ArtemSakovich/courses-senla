package com.company.hotel.clui.actions;

import com.company.api.service.IGuestService;
import com.company.api.service.IMaintenanceService;
import com.company.api.service.IRoomAssignmentService;
import com.company.api.service.IRoomService;
import com.company.model.*;
import com.company.service.GuestService;
import com.company.service.MaintenanceService;
import com.company.service.RoomAssignmentService;
import com.company.service.RoomService;

import java.time.LocalDateTime;

public class Facade {
    private static IGuestService guestService;

    private static IRoomService roomService;

    private static IMaintenanceService maintenanceService;

    private static IRoomAssignmentService roomAssignmentService;

    private static Facade instance;

    private Facade() {
        guestService = GuestService.getInstance();
        roomService = RoomService.getInstance();
        maintenanceService = MaintenanceService.getInstance();
        roomAssignmentService = RoomAssignmentService.getInstance();
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void createTestData() {
        Guest artem = guestService.addGuest("zArtem", "Sakovich", 19);
        Guest artem1 = guestService.addGuest("Artemc", "Sakovich", 12);
        Guest artem2 = guestService.addGuest("bArtem", "Sakovich", 16);
        Guest artem3 = guestService.addGuest("bArtgsem", "Sakeovich", 16);
        Guest artem4 = guestService.addGuest("bArtffem", "Sakaovich", 16);

        Room room1 = roomService.addRoom(11, 112.8, 8, 4);
        Room room2 = roomService.addRoom(33, 14.2, 3, 1);
        Room room3 = roomService.addRoom(36, 6.5, 1, 5);


        Maintenance maintenance1 = maintenanceService.addMaintenance("Cleaning", 123.12, MaintenanceSection.CLEANING);
        Maintenance maintenance2 = maintenanceService.addMaintenance("Cooking", 12.12, MaintenanceSection.FOOD);
        Maintenance maintenance3 = maintenanceService.addMaintenance("Treatment", 1223.12, MaintenanceSection.MEDICAL);
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
        roomService.sortRoomsByNumberOfBeds().forEach(System.out::println);
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
        if (roomAssignmentService.sortRoomsByCheckOutDate().isEmpty()) {
            System.out.println("All rooms are free");
        } else {
            roomAssignmentService.sortRoomsByCheckOutDate().forEach(System.out::println);
        }
    }

    public void getNumberOfFreeRooms() {
        System.out.println(roomService.getNumberOfFreeRooms());
    }

    public void getNumberOfGuests() {
        System.out.println(guestService.getNumberOfGuests());
    }

    public void getFreeRoomsByDate(LocalDateTime dateToCheck) {
        roomService.getFreeRoomsByDate(dateToCheck).forEach(System.out::println);
    }

    public Double getAmountPerStay(Long guestId) {
       return guestService.getAmountOfPaymentForTheRoom(guestId);
    }

    public void getThreeLastGuests(Long roomId) {
        roomService.getThreeLastGuests(roomId).forEach(System.out::println);
        roomService.getThreeLastGuestsCheckInDates(roomId).forEach(System.out::println);
    }

    public void viewListOfMaintenancesOfCertainGuest(Long guestId) {
        roomAssignmentService.getAllMaintenancesOfCertainGuest(guestId).forEach(System.out::println);
    }

    public void sortMaintenancesOfCertainGuestByPrice(Long guestId) {
        maintenanceService.sortMaintenancesOfCertainGuestByPrice(guestId).forEach(System.out::println);
    }

    public void sortMaintenancesOfCertainGuestByOrderDate(Long guestId) {
        roomAssignmentService.sortMaintenancesByOrderDate(guestId).forEach(System.out::println);
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

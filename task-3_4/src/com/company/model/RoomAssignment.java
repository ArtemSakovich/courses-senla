package com.company.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class RoomAssignment extends AEntity {

    private Long id;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomAssignmentStatus roomAssignmentStatus;
    private List<LocalDate> maintenanceOrderDates = new ArrayList<>();
    private List<Maintenance> maintenances = new ArrayList<Maintenance>();

    public RoomAssignment(Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate,
                          RoomAssignmentStatus roomAssignmentStatus) {
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomAssignmentStatus = roomAssignmentStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RoomAssignmentStatus getRoomAssignmentStatus() {
        return roomAssignmentStatus;
    }

    public void setRoomAssignmentStatus(RoomAssignmentStatus roomAssignmentStatus) {
        this.roomAssignmentStatus = roomAssignmentStatus;
    }

    public void setMaintenanceOrderDate(LocalDate orderDate) {
        maintenanceOrderDates.add(orderDate);
    }

    public void setMaintenance(Maintenance maintenance) {
        maintenances.add(maintenance);
    }

    public List<LocalDate> getMaintenanceOrderDates() {
        return maintenanceOrderDates;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public Double getPricePerStay() {
        Double totalMaintenancePrice = 0.0;
        for (Maintenance maintenance : maintenances) {
            totalMaintenancePrice += maintenance.getMaintenancePrice();
        }
        return room.getRoomPrice() * DAYS.between(checkInDate, checkOutDate) + totalMaintenancePrice;
    }

}

package com.company.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class RoomAssignment extends AEntity {

    private Long id;
    private Room room;
    private Guest guest;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RoomAssignmentStatus roomAssignmentStatus;
    private LocalDateTime createdOn;
    private List<Maintenance> maintenances = new ArrayList<>();

    public RoomAssignment(Room room, Guest guest, LocalDateTime checkInDate, LocalDateTime checkOutDate,
                          RoomAssignmentStatus roomAssignmentStatus) {
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.createdOn = LocalDateTime.now();
        this.roomAssignmentStatus = roomAssignmentStatus;
    }

    @Override
    public String toString() {
        return "RoomAssignment{" +
                "id=" + id +
                ", room=" + room +
                ", guest=" + guest +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", roomAssignmentStatus=" + roomAssignmentStatus +
                ", createdOn=" + createdOn +
                ", maintenances=" + maintenances +
                '}';
    }

    public Room getRoom() {
        return room;
    }

    public Integer getRoomNumber() {
        return room.getRoomNumber();
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

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RoomAssignmentStatus getRoomAssignmentStatus() {
        return roomAssignmentStatus;
    }

    public void setRoomAssignmentStatus(RoomAssignmentStatus roomAssignmentStatus) {
        this.roomAssignmentStatus = roomAssignmentStatus;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setMaintenance(Maintenance maintenance) {
        maintenances.add(maintenance);
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

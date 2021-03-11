package com.company.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class RoomAssignment extends AEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAssignment that = (RoomAssignment) o;
        return Objects.equals(room, that.room) && Objects.equals(guest, that.guest) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate) && roomAssignmentStatus == that.roomAssignmentStatus && Objects.equals(createdOn, that.createdOn) && Objects.equals(maintenances, that.maintenances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, guest, checkInDate, checkOutDate, roomAssignmentStatus, createdOn, maintenances);
    }

    @Override
    public String toString() {
        return "Assignment #" + getId() + "; Guest: " + guest.getName() +
                " " + guest.getSurname() + "; Room: " + room.getRoomNumber() +
                "; Validity: " + getCheckInDate().toLocalDate() + " - " + getCheckOutDate().toLocalDate() +
                "; Status: " + getRoomAssignmentStatus().toString();
    }
}

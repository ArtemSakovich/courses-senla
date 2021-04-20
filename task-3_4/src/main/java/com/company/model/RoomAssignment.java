package com.company.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomAssignment extends AEntity {
    private Long roomId;
    private Long guestId;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private RoomAssignmentStatus roomAssignmentStatus;
    private Timestamp createdOn;
    private List<Maintenance> maintenances = new ArrayList<>();

    public RoomAssignment(Long roomId, Long guestId, Timestamp checkInDate, Timestamp checkOutDate,
                          RoomAssignmentStatus roomAssignmentStatus) {
        this.roomId = roomId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomAssignmentStatus = roomAssignmentStatus;
        this.createdOn = Timestamp.valueOf(LocalDateTime.now());
    }

    public RoomAssignment() {
        maintenances = new ArrayList<>();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RoomAssignmentStatus getRoomAssignmentStatus() {
        return roomAssignmentStatus;
    }

    public void setRoomAssignmentStatus(RoomAssignmentStatus roomAssignmentStatus) {
        this.roomAssignmentStatus = roomAssignmentStatus;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public void addMaintenance(Maintenance maintenance) {
        maintenances.add(maintenance);
    }

}
package com.company.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "roomassignment")
public class RoomAssignment extends AEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomId")
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guestId")
    private Guest guest;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    @Enumerated(EnumType.STRING)
    private RoomAssignmentStatus roomAssignmentStatus;
    private Timestamp createdOn;
    @OneToMany(mappedBy = "roomAssignment")
    private List<OrderedMaintenance> maintenances = new ArrayList<>();

    public RoomAssignment(Room room, Guest guest, Timestamp checkInDate, Timestamp checkOutDate,
                          RoomAssignmentStatus roomAssignmentStatus, Timestamp createdOn) {
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomAssignmentStatus = roomAssignmentStatus;
        this.createdOn = createdOn;
    }

    public RoomAssignment() {
        maintenances = new ArrayList<>();
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

    public List<OrderedMaintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<OrderedMaintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public void addMaintenance(OrderedMaintenance maintenance) {
        maintenances.add(maintenance);
    }

}
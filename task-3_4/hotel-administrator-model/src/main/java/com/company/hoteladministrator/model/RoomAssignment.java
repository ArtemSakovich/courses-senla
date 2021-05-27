package com.company.hoteladministrator.model;

import com.company.hoteladministrator.model.enums.RoomAssignmentStatus;
import com.company.hoteladministrator.model.generic.AEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_assignments")
public class RoomAssignment extends AEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @Column(name = "check_in_date")
    private Timestamp checkInDate;
    @Column(name = "check_out_date")
    private Timestamp checkOutDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "room_assignment_status")
    private RoomAssignmentStatus roomAssignmentStatus;
    @Column(name = "created_on")
    private Timestamp createdOn;
    @OneToMany(mappedBy = "roomAssignment", cascade = CascadeType.ALL)
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
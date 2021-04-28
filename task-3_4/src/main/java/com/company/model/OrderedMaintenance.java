package com.company.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderedmaintenances")
public class OrderedMaintenance extends AEntity{
    @ManyToOne
    @JoinColumn(name = "roomAssignmentId")
    RoomAssignment roomAssignment;

    @ManyToOne
    @JoinColumn(name = "maintenanceId")
    Maintenance maintenance;

    Timestamp orderDate;

    public OrderedMaintenance() {
        orderDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public OrderedMaintenance(RoomAssignment roomAssignment, Maintenance maintenance, Timestamp orderDate) {
        this.roomAssignment = roomAssignment;
        this.maintenance = maintenance;
        this.orderDate = orderDate;
    }

    public RoomAssignment getRoomAssignment() {
        return roomAssignment;
    }

    public void setRoomAssignment(RoomAssignment roomAssignment) {
        this.roomAssignment = roomAssignment;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}

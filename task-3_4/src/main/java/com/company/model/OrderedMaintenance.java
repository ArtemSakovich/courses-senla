package com.company.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordered_maintenances")
public class OrderedMaintenance extends AEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_assignment_id")
    RoomAssignment roomAssignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_id")
    Maintenance maintenance;
    @Column(name = "order_date")
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

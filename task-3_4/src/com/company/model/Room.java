package com.company.model;

import com.company.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Room extends AEntity {
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private Double roomPrice;
    private Integer numberOfBeds;
    private Long id;
    private List<Guest> tenants = new ArrayList<>();

    public Room(int roomNumber, double roomPrice, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.roomStatus = RoomStatus.FREE;
        this.roomPrice = roomPrice;
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public List<Guest> getTenants() {
        return tenants;
    }

    public void setTenants(List<Guest> tenants) {
        this.tenants.addAll(tenants);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + roomNumber;
        result = 31 * result + roomStatus.hashCode();
        result = (int) (31 * result + roomPrice);
        result = 31 * result + numberOfBeds;
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Room)) {
            return false;
        }

        Room room = (Room) o;

        return room.roomNumber.equals(roomNumber) &&
                room.roomStatus.equals(roomStatus) &&
                room.roomPrice.equals(roomPrice) &&
                room.numberOfBeds.equals(numberOfBeds);
    }

    @Override
    public String toString() {
        return "Room #" + getId() + ". Room number: " + roomNumber +
                ". Number of beds: " + numberOfBeds + ". Price: " + roomPrice +
                ". Status: " + roomStatus.toString();
    }
}
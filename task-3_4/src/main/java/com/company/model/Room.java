package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Room extends AEntity {
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private Double roomPrice;
    private Integer numberOfBeds;
    private Integer numberOfStars;
    private transient List<RoomAssignment> roomAssignments;

    public Room(Integer roomNumber, Double roomPrice, Integer numberOfBeds, Integer numberOfStars) {
        this.roomNumber = roomNumber;
        this.roomStatus = RoomStatus.FREE;
        this.roomPrice = roomPrice;
        this.numberOfBeds = numberOfBeds;
        this.numberOfStars = numberOfStars;
    }

    public Room() {
        roomAssignments = new ArrayList<>();
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

    public void setRoomAssignments(List<RoomAssignment> roomAssignments) {
        this.roomAssignments.addAll(roomAssignments);
    }

    public List<RoomAssignment> getRoomAssignments() {
        if (roomAssignments == null) {
            roomAssignments = new ArrayList<>();
        }
        return roomAssignments;
    }

    public void addRoomAssignment(RoomAssignment roomAssignment) {
        getRoomAssignments().add(roomAssignment);
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public boolean isFree() {
        return roomStatus.equals(RoomStatus.FREE);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + roomNumber;
        result = 31 * result + numberOfStars;
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
                room.numberOfBeds.equals(numberOfBeds) &&
                room.numberOfStars.equals(numberOfStars);
    }

    @Override
    public String toString() {
        return "Room #" + getId() + "; Room number: " + roomNumber + "; Number of stars: "
                + numberOfStars + "; Number of beds: " + numberOfBeds +
                "; Price: " + roomPrice + "; Status: " + roomStatus.toString();
    }
}
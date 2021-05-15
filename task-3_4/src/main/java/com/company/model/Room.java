package com.company.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "rooms")
public class Room extends AEntity {
    @Column(name = "room_number")
    private Integer roomNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "room_status")
    private RoomStatus roomStatus;
    @Column(name = "room_price")
    private Double roomPrice;
    @Column(name = "number_of_beds")
    private Integer numberOfBeds;
    @Column(name = "number_of_stars")
    private Integer numberOfStars;
    @Transient
    private Integer numberOfTenants;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomAssignment> roomAssignments;

    public Room(Integer numberOfBeds, Integer numberOfStars, Integer roomNumber, Double roomPrice) {
        this.numberOfBeds = numberOfBeds;
        this.numberOfStars = numberOfStars;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomStatus = RoomStatus.FREE;
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

    public Integer getNumberOfTenants() {
        return (int) roomAssignments.stream().filter(roomAssignment ->
                RoomAssignmentStatus.ACTIVE.equals(roomAssignment.getRoomAssignmentStatus())).count();
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
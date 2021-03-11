package com.company.model;

import java.util.List;

public class AllData {
    private List<Guest> allGuests;
    private List<Maintenance> allMaintenances;
    private List<Room> allRooms;
    private List<RoomAssignment> allRoomAssignments;

    public List<Guest> getAllGuests() {
        return allGuests;
    }

    public void setAllGuests(List<Guest> allGuests) {
        this.allGuests = allGuests;
    }

    public List<Maintenance> getAllMaintenances() {
        return allMaintenances;
    }

    public void setAllMaintenances(List<Maintenance> allMaintenances) {
        this.allMaintenances = allMaintenances;
    }

    public List<Room> getAllRooms() {
        return allRooms;
    }

    public void setAllRooms(List<Room> allRooms) {
        this.allRooms = allRooms;
    }

    public List<RoomAssignment> getAllRoomAssignments() {
        return allRoomAssignments;
    }

    public void setAllRoomAssignments(List<RoomAssignment> allRoomAssignments) {
        this.allRoomAssignments = allRoomAssignments;
    }

    @Override
    public String toString() {
        return "AllData{" +
                "allGuests=" + allGuests +
                ", allMaintenances=" + allMaintenances +
                ", allRooms=" + allRooms +
                ", allRoomAssignments=" + allRoomAssignments +
                '}';
    }
}

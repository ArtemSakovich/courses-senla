package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Guest extends AEntity implements Comparable<Guest> {

    private String name;
    private String surname;
    private Integer age;
    private Long id;
    private List<RoomAssignment> roomAssignments = new ArrayList<>();

    public Guest(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<RoomAssignment> getRoomAssignments() {
        return roomAssignments;
    }

    public void setRoomAssignment(RoomAssignment roomAssignment) {
        roomAssignments.add(roomAssignment);
    }

    public List<RoomAssignment> getActiveRoomAssignments() {
        List<RoomAssignment> activeRoomAssignments = new ArrayList<>();
        for (RoomAssignment roomAssignment : getRoomAssignments()) {
            if (roomAssignment.getRoomAssignmentStatus().equals(RoomAssignmentStatus.ACTIVE)) {
                activeRoomAssignments.add(roomAssignment);
            }
        }
        return activeRoomAssignments;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Guest)) {
            return false;
        }

        Guest guest = (Guest) o;

        return guest.name.equals(name) &&
                guest.surname.equals(surname) &&
                guest.age.equals(age);
    }

    @Override
    public int compareTo(Guest g) {                 // most requested sort
        String thisFullName = this.name + this.surname;
        String gFullName = g.name + g.surname;
        return thisFullName.toLowerCase().compareTo(gFullName.toLowerCase());
    }

    @Override
    public String toString() {
        return "Guest #" + getId() + ". Name: " + name +
                " " + surname + ". Age: " + age;
    }
}
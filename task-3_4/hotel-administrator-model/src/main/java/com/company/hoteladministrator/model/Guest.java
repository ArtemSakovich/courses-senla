package com.company.hoteladministrator.model;

import com.company.hoteladministrator.model.enums.RoomAssignmentStatus;
import com.company.hoteladministrator.model.generic.AEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guests")
public class Guest extends AEntity {

    private String name;
    private String surname;
    private Integer age;
    @Transient
    private Boolean hasActiveRoomAssignment;
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomAssignment> roomAssignments;

    public Guest(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Guest() {
        roomAssignments = new ArrayList<>();
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

    public void setRoomAssignments(List<RoomAssignment> roomAssignments) {
        this.roomAssignments.addAll(roomAssignments);
    }

    public Boolean getHasActiveRoomAssignment() {
        return roomAssignments.stream().anyMatch(roomAssignment ->
                RoomAssignmentStatus.ACTIVE.equals(roomAssignment.getRoomAssignmentStatus()));
    }

    public void addRoomAssignment(RoomAssignment roomAssignment) {
        roomAssignments.add(roomAssignment);
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
    public String toString() {
        return "Guest #" + getId() + "; Name: " + name +
                " " + surname + "; Age: " + age;
    }
}
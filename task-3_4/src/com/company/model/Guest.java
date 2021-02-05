package com.company.model;

import com.company.util.IdGenerator;

public class Guest extends AEntity {
    private String name;
    private String surname;
    private Integer age;
    private Long id;
    private Room room;

    public Guest(String name, String surname, int age) {
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
                guest.age == age;
    }

    @Override
    public String toString() {
        if (room == null) {
            return "Guest #" + getId() + ". Name: " + name +
                    " " + surname + ". Age: " + age;
        } else {
            return "Guest #" + getId() + ". Name: " + name +
                    " " + surname + ". Age: " + age + ". Room №" + room.getRoomNumber();
        }
    }
}

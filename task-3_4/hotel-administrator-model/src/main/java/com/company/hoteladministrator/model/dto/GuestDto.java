package com.company.hoteladministrator.model.dto;

public class GuestDto extends AbstractDto {

    private String name;
    private String surname;
    private Integer age;
    private Boolean hasActiveRoomAssignment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getHasActiveRoomAssignment() {
        return hasActiveRoomAssignment;
    }

    public void setHasActiveRoomAssignment(Boolean hasActiveRoomAssignment) {
        this.hasActiveRoomAssignment = hasActiveRoomAssignment;
    }
}

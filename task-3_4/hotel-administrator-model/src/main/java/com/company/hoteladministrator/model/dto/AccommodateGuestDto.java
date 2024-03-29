package com.company.hoteladministrator.model.dto;

import com.company.hoteladministrator.model.dto.generic.AbstractDto;

public class AccommodateGuestDto extends AbstractDto {

    private Long guestId;
    private Long roomId;
    private Integer numberOfDays;

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}

package com.company.api.service;

import com.company.dto.AccommodateGuestDto;
import com.company.dto.GuestDto;

import java.util.List;

public interface IGuestService {

    GuestDto addGuest(GuestDto guestDto);

    void accommodateToRoom(AccommodateGuestDto accommodateGuestDto);

    void evictFromRoom(Long guestId);

    List<GuestDto> getAllGuests() ;

    Integer getNumberOfGuests();

    void orderMaintenance(Long guestId, Long maintenanceId);

    Double getAmountOfPaymentForTheRoom(Long guestId);

    GuestDto changeGuestInfo(GuestDto guestDto);

    List<GuestDto> getSortedGuests(String paramToSort);

    List<GuestDto> getLastGuests(Long roomId);

    void deleteGuest(Long guestId);
}

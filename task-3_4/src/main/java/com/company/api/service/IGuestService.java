package com.company.api.service;

import com.company.model.Guest;

import java.time.LocalDateTime;
import java.util.List;

public interface IGuestService {
    Guest addGuest(String name, String surname, Integer age);

    void update(Guest updatedGuest);

    Guest getById(Long id);

    void accommodateToRoom(Long guestId, Long roomId, LocalDateTime checkOutDate);

    void evictFromRoom(Long guestId);

    List<Guest> getAllGuests();

    Integer getNumberOfGuests();

    List<Guest> sortGuestsABC();

    void orderMaintenance(Long guestId, Long maintenanceId);

    Double getAmountOfPaymentForTheRoom(Long guestId);
}

package com.company.api.service;

import com.company.model.Guest;

import java.time.LocalDate;
import java.util.List;

public interface IGuestService {
    public Guest addGuest(String name, String surname, Integer age);

    void update(Guest updatedGuest);

    Guest getById(Long id);

    void accommodateToRoom(Long guestId, Long roomId, LocalDate checkOutDate);

    void evictFromRoom(Long guestId);

    List<Guest> getAllGuests();

    Integer getNumberOfGuests();

    public List<Guest> sortGuestsABC();

    public void orderMaintenance(Long guestId, Long maintenanceId);

    public Double getAmountOfPaymentForTheRoom(Long guestId);
}

package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Maintenance;
import com.company.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IGuestService {
    public Guest addGuest(String name, String surname, Integer age);

    void update(Guest updatedGuest);

    Guest getById(Long id);

    void flipToRoom(Guest guest, Room room, LocalDate checkOutDate);

    void evictFromRoom(Guest guest);

    List<Guest> getAllGuests();

    Integer getNumberOfGuests();

    public List<Guest> sortGuestsABC();

    public void orderMaintenance(Guest guest, Maintenance maintenance);
}

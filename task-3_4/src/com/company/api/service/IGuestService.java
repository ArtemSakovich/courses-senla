package com.company.api.service;

import com.company.model.Guest;
import com.company.model.Room;

public interface IGuestService {
    public Guest addGuest(String name, String surname, Integer age);

    void update(Guest updatedGuest);

    Guest getById(Long id);

    void flipToRoom(Guest guest, Room room);

    void evictFromRoom(Guest guest);
}

package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.service.IGuestService;
import com.company.model.*;
import com.company.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GuestService implements IGuestService {

    private final IGuestDao guestDao;

    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    public Guest addGuest(String name, String surname, Integer age) {
        Guest guest = new Guest(name, surname, age);
        guest.setId(IdGenerator.generateGuestId());
        guestDao.save(guest);
        return guest;
    }

    @Override
    public void update(Guest updatedGuest) {
        guestDao.update(updatedGuest);
    }

    @Override
    public Guest getById(Long id) {
        return guestDao.getById(id);
    }

    @Override
    public void flipToRoom(Guest guest, Room room, LocalDate checkOutDate) {
        if (room.getRoomStatus().equals(RoomStatus.FREE)) {
            RoomAssignment roomAssignment = new RoomAssignment(room, guest, LocalDate.now(), checkOutDate,
                    RoomAssignmentStatus.ACTIVE);
            if (room.getNumberOfBeds().equals(room.getTenants().size())) {
                room.setRoomStatus(RoomStatus.OCCUPIED);
            }
        } else {
            System.out.println("Unfortunately, this room is " +
                    room.getRoomStatus().toString().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public void evictFromRoom(Guest guest) {
        for (RoomAssignment roomAssignment : guest.getRoomAssignments()) {
            if (roomAssignment.getRoomAssignmentStatus().equals(RoomAssignmentStatus.ACTIVE)) {
                roomAssignment.setRoomAssignmentStatus(RoomAssignmentStatus.CLOSED);
                System.out.println(guest.getName() + " " + guest.getSurname() +
                        " has been evict from room №" + roomAssignment.getRoom());
            } else {
                System.out.println("Unfortunately, " + guest.getName() + " " +
                        guest.getSurname() + " currently does not live in any of the rooms");
            }
        }
    }

    @Override
    public void orderMaintenance(Guest guest, Maintenance maintenance) {
        if(guest.getActiveRoomAssignments().isEmpty()) {
            System.out.println("Unfortunately, " + guest.getName() + " " +
                    guest.getSurname() + " currently currently not a hotel guest");
        } else {
            for (RoomAssignment roomAssignment : guest.getActiveRoomAssignments()) {
                maintenance.setOrderDate(LocalDate.now());
                roomAssignment.setMaintenanceOrderDate(LocalDate.now());
                roomAssignment.setMaintenance(maintenance);
            }
        }
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestDao.getAll();
    }

    @Override
    public Integer getNumberOfGuests() {
        return getAllGuests().size();
    }

    @Override
    public List<Guest> sortGuestsABC() {
        List<Guest> guestsToSort = new ArrayList<>(getAllGuests());
        Collections.sort(guestsToSort);
        return guestsToSort;
    }
}
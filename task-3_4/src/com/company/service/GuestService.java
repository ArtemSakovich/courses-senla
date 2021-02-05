package com.company.service;

import com.company.api.dao.IGuestDao;
import com.company.api.service.IGuestService;
import com.company.model.Guest;
import com.company.model.Room;
import com.company.model.RoomStatus;
import com.company.util.IdGenerator;

import java.util.ArrayList;
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

    public void flipToRoom(Guest guest, Room room) {
        if (room.getRoomStatus().equals(RoomStatus.FREE)) {
            guest.setRoom(room);
            List<Guest> guestToFlip = new ArrayList<>();
            guestToFlip.add(guest);
            room.setTenants(guestToFlip);
            if (room.getTenants().size() >= room.getNumberOfBeds()) {
                room.setRoomStatus(RoomStatus.OCCUPIED);
            }
            System.out.println(guest.getName() + " " + guest.getSurname() +
                    " has been flipped to room №" + room.getRoomNumber());
        } else {
            System.out.println("Unfortunately, this room is " +
                    room.getRoomStatus().toString().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public void evictFromRoom(Guest guest) {
        if (guest.getRoom() != null) {
            List<Guest> guestsFromRoomForEviction = new ArrayList<>(guest.getRoom().getTenants());
            guestsFromRoomForEviction.remove(guest);
            guest.getRoom().setTenants(guestsFromRoomForEviction);
            System.out.println(guest.getName() + " " + guest.getSurname() +
                    " has been evict from room №" + guest.getRoom().getRoomNumber());
            guest.setRoom(null);
        }else {
            System.out.println("Unfortunately, " + guest.getName() + " " +
                    guest.getSurname() + " does not live in any of the rooms");
        }
    }
}
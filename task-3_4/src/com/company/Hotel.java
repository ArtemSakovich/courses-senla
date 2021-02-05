package com.company;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IMaintenanceDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IGuestService;
import com.company.api.service.IMaintenanceService;
import com.company.api.service.IRoomService;
import com.company.dao.GuestDao;
import com.company.dao.MaintenanceDao;
import com.company.dao.RoomDao;
import com.company.model.Guest;
import com.company.model.Maintenance;
import com.company.model.Room;
import com.company.service.GuestService;
import com.company.service.MaintenanceService;
import com.company.service.RoomService;
import com.company.util.IdGenerator;

public class Hotel {
    private static final IGuestService guestService = new GuestService(new GuestDao());

    private static final IRoomService roomService = new RoomService(new RoomDao());

    private static final IMaintenanceService maintenanceService = new MaintenanceService(new MaintenanceDao());

    public static void main(String[] args) {
        Guest artem = guestService.addGuest("Artem", "Sakovich", 19);
        Guest updatedGuest = guestService.getById(artem.getId());
        System.out.println(updatedGuest);
        updatedGuest.setName(updatedGuest.getName() + " updated.");
        guestService.update(updatedGuest);
        System.out.println(guestService.getById(1L));
        Guest artem1 = guestService.addGuest("Artem1", "Sakovich1", 20);

        Room firstRoom = roomService.addRoom(18, 12.14, 1);
        System.out.println(roomService.getById(firstRoom.getId()));

        Maintenance cleanRoom = maintenanceService.addMaintenance("Cleaning", 8.35);
        System.out.println(maintenanceService.getById(cleanRoom.getId()));

        guestService.flipToRoom(guestService.getById(1L), firstRoom);
        System.out.println(guestService.getById(1L));
        guestService.flipToRoom(guestService.getById(2L), firstRoom);
        System.out.println(guestService.getById(2L));

        guestService.evictFromRoom(guestService.getById(1L));
        System.out.println(guestService.getById(1L));
        guestService.evictFromRoom(guestService.getById(1L));
        System.out.println(guestService.getById(1L));
    }
}

package com.company;

import com.company.api.service.IGuestService;
import com.company.api.service.IMaintenanceService;
import com.company.api.service.IRoomService;
import com.company.dao.GuestDao;
import com.company.dao.MaintenanceDao;
import com.company.dao.RoomDao;
import com.company.service.GuestService;
import com.company.service.MaintenanceService;
import com.company.service.RoomService;

public class Hotel {
    private static final IGuestService guestService = new GuestService(new GuestDao());

    private static final IRoomService roomService = new RoomService(new RoomDao());

    private static final IMaintenanceService maintenanceService = new MaintenanceService(new MaintenanceDao());

    public static void main(String[] args) {

        /*Guest artem = guestService.addGuest("Artem", "Sakovich", 19);  // check for add method
        Guest artem1 = guestService.addGuest("ZzArtem", "Saskovich", 19);
        Guest artem2 = guestService.addGuest("bArtem", "Sakovaich", 19);
        Guest artem3 = guestService.addGuest("qArtem", "Sakobvich", 19);*/

        /*Collections.sort(guestService.getAllGuests());         // check for sort method
        System.out.println(guestService.getAllGuests());*/

        /*Guest updatedGuest = guestService.getById(artem.getId());   // check for update method
        System.out.println(updatedGuest);
        updatedGuest.setName(updatedGuest.getName() + " updated.");
        guestService.update(updatedGuest);
        System.out.println(guestService.getById(1L));

        Guest artem1 = guestService.addGuest("Artem1", "Sakovich1", 20);*/ // check for add method

        /*Room firstRoom = roomService.addRoom(18, 12.14, 1, 3);  // check for add method
        System.out.println(roomService.getById(firstRoom.getId()));

        Room firstRoom1 = roomService.addRoom(128, 132.14, 11, 34);  // check for add method
        System.out.println(roomService.getById(firstRoom1.getId()));

        Room afirstRoom = roomService.addRoom(2, 4.14, 2, 344); // check for add method
        System.out.println(roomService.getById(afirstRoom.getId()));

        roomService.changeRoomStatus(firstRoom1, RoomStatus.OCCUPIED);  // check for change room status method

        Collections.sort(roomService.getAllRooms(), new numberOfBedsComparator());  // check for sort method
        System.out.println(roomService.getAllRooms());

        Collections.sort(roomService.getAllFreeRooms(), new numberOfBedsComparator());
        System.out.println(roomService.getAllFreeRooms());

        System.out.println(roomService.getNumberOfFreeRooms());   // check for number of free rooms method*/

       /* Collections.sort(roomService.getAllRooms());  // check for sort method
        System.out.println(roomService.getAllRooms());*/

       /* Maintenance cleanRoom = maintenanceService.addMaintenance("Clean room", 8.35, "Cleaning"); // check for add method
        System.out.println(maintenanceService.getById(cleanRoom.getId()));

        Maintenance bringLunch = maintenanceService.addMaintenance("Bring Luncg", 5.35, "Brining Food"); // check for add method
        System.out.println(maintenanceService.getById(bringLunch.getId()));

        Maintenance washСlothes = maintenanceService.addMaintenance("Wash clothes", 2.35, "Washing"); // check for add method
        System.out.println(maintenanceService.getById(washСlothes.getId()));

        System.out.println(maintenanceService.getAllMaintenances());
        Collections.sort(maintenanceService.getAllMaintenances(), new maintenancePriceComparator());  // check for sort methods
        System.out.println(maintenanceService.getAllMaintenances());
        Collections.sort(maintenanceService.getAllMaintenances(), new maintenanceSectionComparator());
        System.out.println(maintenanceService.getAllMaintenances());*/

        /*guestService.flipToRoom(guestService.getById(1L), firstRoom);  // check for flip method
        System.out.println(guestService.getById(1L));
        guestService.flipToRoom(guestService.getById(2L), firstRoom);
        System.out.println(guestService.getById(2L));

        guestService.evictFromRoom(guestService.getById(1L));  // check for evict method
        System.out.println(guestService.getById(1L));
        guestService.evictFromRoom(guestService.getById(1L));
        System.out.println(guestService.getById(1L));*/
    }
}

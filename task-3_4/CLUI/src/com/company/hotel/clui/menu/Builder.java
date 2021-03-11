package com.company.hotel.clui.menu;

import com.company.hotel.clui.actions.guest.AddGuest;
import com.company.hotel.clui.actions.guest.EvictFromRoom;
import com.company.hotel.clui.actions.guest.FlipToRoom;
import com.company.hotel.clui.actions.guest.GetAmountPerStay;
import com.company.hotel.clui.actions.maintenance.*;
import com.company.hotel.clui.actions.room.*;
import com.company.hotel.clui.facade.Facade;
import com.company.service.SerializeService;

public class Builder {

    private static Builder instance;
    private Menu rootMenu;

    private Builder() {
        buildMenu();
    }

    public static Builder getInstance() {
        if (instance == null) {
            instance = new Builder();
        }
        return instance;
    }

    public void buildMenu() {
        rootMenu = new Menu();
        rootMenu.addMenuItem(new MenuItem("Download data", () -> SerializeService.getInstance().deserialize(), rootMenu));
        rootMenu.addMenuItem(new MenuItem("Guest Menu", () -> { }, createGuestMenu()));
        rootMenu.addMenuItem(new MenuItem("Room Menu", () -> { }, createRoomMenu()));
        rootMenu.addMenuItem(new MenuItem("Maintenance Menu", () -> { }, createMaintenanceMenu()));
        rootMenu.addMenuItem(new MenuItem("Save data", () -> SerializeService.getInstance().serialize() , rootMenu));
        rootMenu.addMenuItem(new MenuItem("Exit", () -> {
            System.exit(0);
        }, null));

    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private Menu createRoomMenu() {
        Menu roomMenu = new Menu();
        roomMenu.addMenuItem(new MenuItem("Add room", new AddRoom(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Change room status", new ChangeRoomStatus(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Change room price", new ChangeRoomPrice(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View the list of rooms", () -> Facade.getInstance().viewListOfRooms(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View the list of free rooms", () -> Facade.getInstance().viewListOfFreeRooms(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Get total number of free rooms", () -> Facade.getInstance().getNumberOfFreeRooms(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View three last guests of the room", new ViewThreeLastGuestsOfRoom(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View the list of rooms that will be free by a certain date in the future",
                                            new GetFreeRoomsByDate(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View details of room",
                new GetRoomDetails(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Sort rooms by", () -> { }, createSortRoomMenu()));
        roomMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return roomMenu;
    }

    private Menu createGuestMenu() {
        Menu guestMenu = new Menu();
        guestMenu.addMenuItem(new MenuItem("Add guest", new AddGuest(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Check guest into room", new FlipToRoom(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Evict guest from room", new EvictFromRoom(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("View the list of all guests", () -> Facade.getInstance().viewListOfGuests(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Get total number of guests who live in the hotel", () -> Facade.getInstance().getNumberOfGuests(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Get amount per stay of guest", new GetAmountPerStay(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Sort guests by", () -> { }, createSortGuestMenu()));
        guestMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return guestMenu;
    }

    private Menu createMaintenanceMenu() {
        Menu maintenanceMenu = new Menu();
        maintenanceMenu.addMenuItem(new MenuItem("Add maintenance", new AddMaintenance(), maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("Change maintenance price", new ChangeMaintenancePrice(), maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("View list of maintenances of certain guest",
                                                new ViewListOfMaintenancesOfCerainGuest(), maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("Order maintenance", new OrderMaintenance(), maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("Sort maintenances by", () -> { }, createSortMaintenanceMenu()));
        maintenanceMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return maintenanceMenu;
    }

    private Menu createSortRoomMenu() {
        Menu sortRoomMenu = new Menu();
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by price", () -> Facade.getInstance().sortRoomsByPrice(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by capacity", () -> Facade.getInstance().sortRoomsByCapacity(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by rating", () -> Facade.getInstance().sortRoomsByNumberOfStars(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort free rooms by price", () -> Facade.getInstance().sortFreeRoomsByPrice(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort free rooms by capacity", () -> Facade.getInstance().sortFreeRoomsByCapacity(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort free rooms by rating", () -> Facade.getInstance().sortFreeRoomsByNumberOfStars(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by release date", () -> Facade.getInstance().sortRoomsByCheckOutDate(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return sortRoomMenu;
    }

    private Menu createSortGuestMenu() {
        Menu sortGuestMenu = new Menu();
        sortGuestMenu.addMenuItem(new MenuItem("Sort guests in alphabetical order", () -> Facade.getInstance().sortGuestsABC(), sortGuestMenu));
        sortGuestMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return sortGuestMenu;
    }

    private Menu createSortMaintenanceMenu() {
        Menu sortMaintenanceMenu = new Menu();
        sortMaintenanceMenu.addMenuItem(new MenuItem("Sort maintenances of certain guest by price",
                new SortMaintenancesOfCertainGuestByPrice(), sortMaintenanceMenu));
        sortMaintenanceMenu.addMenuItem(new MenuItem("Sort maintenances of certain guest by date of order",
                new SortMaintenancesOfCertainGuestByOrderDate(), sortMaintenanceMenu));
        sortMaintenanceMenu.addMenuItem(new MenuItem("Sort all maintenances by section in alphabetical order",
                () -> Facade.getInstance().sortAllMaintenancesBySectionABC(), sortMaintenanceMenu));
        sortMaintenanceMenu.addMenuItem(new MenuItem("Sort all maintenances by price",
                () -> Facade.getInstance().sortAllMaintenancesByPrice(), sortMaintenanceMenu));
        sortMaintenanceMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return sortMaintenanceMenu;
    }
    
    public Menu createRetryMenu(MenuItem itemToRetry) {
        Menu retryMenu = new Menu();
        retryMenu.addMenuItem(new MenuItem("Retry", itemToRetry.getAction(),  itemToRetry.getNextMenu()));
        retryMenu.addMenuItem(new MenuItem(("Cancel"), () -> { }, itemToRetry.getNextMenu()));
        return retryMenu;
    }
}

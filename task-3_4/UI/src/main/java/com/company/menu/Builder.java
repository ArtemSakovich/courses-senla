package com.company.menu;

import com.company.actions.guest.AddGuest;
import com.company.actions.guest.EvictFromRoom;
import com.company.actions.guest.FlipToRoom;
import com.company.actions.guest.GetAmountPerStay;
import com.company.actions.maintenance.*;
import com.company.actions.room.*;
import com.company.facade.Facade;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

@DependencyClass
public class Builder {
    @DependencyComponent
    private Menu rootMenu;
    @DependencyComponent
    private Facade facade;
    @DependencyComponent
    private AddRoom addRoomAction;
    @DependencyComponent
    private ChangeRoomStatus changeRoomStatusAction;
    @DependencyComponent
    private ChangeRoomPrice changeRoomPrice;
    @DependencyComponent
    private ViewThreeLastGuestsOfRoom viewThreeLastGuestsOfRoom;
    @DependencyComponent
    private GetFreeRoomsByDate getFreeRoomsByDate;
    @DependencyComponent
    private GetRoomDetails getRoomDetails;
    @DependencyComponent
    private AddGuest addGuest;
    @DependencyComponent
    private FlipToRoom flipToRoom;
    @DependencyComponent
    private EvictFromRoom evictFromRoom;
    @DependencyComponent
    private GetAmountPerStay getAmountPerStay;
    @DependencyComponent
    private AddMaintenance addMaintenance;
    @DependencyComponent
    private ChangeMaintenancePrice changeMaintenancePrice;
    @DependencyComponent
    private ViewListOfMaintenancesOfCerainGuest viewListOfMaintenancesOfCerainGuest;
    @DependencyComponent
    private OrderMaintenance orderMaintenance;

    private Builder() {
        //buildMenu();
    }

    public void buildMenu() {
        rootMenu = new Menu();
        rootMenu.addMenuItem(new MenuItem("Download data", () -> facade.deserialize(), rootMenu));
        rootMenu.addMenuItem(new MenuItem("Guest Menu", () -> { }, createGuestMenu()));
        rootMenu.addMenuItem(new MenuItem("Room Menu", () -> { }, createRoomMenu()));
        rootMenu.addMenuItem(new MenuItem("Maintenance Menu", () -> { }, createMaintenanceMenu()));
        rootMenu.addMenuItem(new MenuItem("Save data", () -> facade.serialize() , rootMenu));
        rootMenu.addMenuItem(new MenuItem("Exit", () -> {
            System.exit(0);
        }, null));
    }

    public Menu getRootMenu() {
        if (rootMenu == null) {
            buildMenu();
        }
        return rootMenu;
    }

    private Menu createRoomMenu() {
        Menu roomMenu = new Menu();
        roomMenu.addMenuItem(new MenuItem("Add room", addRoomAction, roomMenu));
        roomMenu.addMenuItem(new MenuItem("Change room status", changeRoomStatusAction, roomMenu));
        roomMenu.addMenuItem(new MenuItem("Change room price", changeRoomPrice, roomMenu));
        roomMenu.addMenuItem(new MenuItem("View the list of rooms", () -> facade.viewListOfRooms(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View the list of free rooms", () -> facade.viewListOfFreeRooms(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Get total number of free rooms", () -> facade.getNumberOfFreeRooms(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("View three last guests of the room", viewThreeLastGuestsOfRoom, roomMenu));
        roomMenu.addMenuItem(new MenuItem("View the list of rooms that will be free by a certain date in the future",
                                                 getFreeRoomsByDate, roomMenu));
        roomMenu.addMenuItem(new MenuItem("View details of room",
                getRoomDetails, roomMenu));
        roomMenu.addMenuItem(new MenuItem("Sort rooms by", () -> { }, createSortRoomMenu()));
        roomMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return roomMenu;
    }

    private Menu createGuestMenu() {
        Menu guestMenu = new Menu();
        guestMenu.addMenuItem(new MenuItem("Add guest", addGuest, guestMenu));
        guestMenu.addMenuItem(new MenuItem("Check guest into room", flipToRoom, guestMenu));
        guestMenu.addMenuItem(new MenuItem("Evict guest from room", evictFromRoom, guestMenu));
        guestMenu.addMenuItem(new MenuItem("View the list of all guests", () -> facade.viewListOfGuests(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Get total number of guests who live in the hotel", () -> facade.getNumberOfGuests(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Get amount per stay of guest", getAmountPerStay, guestMenu));
        guestMenu.addMenuItem(new MenuItem("Sort guests by", () -> { }, createSortGuestMenu()));
        guestMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return guestMenu;
    }

    private Menu createMaintenanceMenu() {
        Menu maintenanceMenu = new Menu();
        maintenanceMenu.addMenuItem(new MenuItem("Add maintenance", addMaintenance, maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("Change maintenance price", changeMaintenancePrice, maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("View list of maintenances of certain guest",
                viewListOfMaintenancesOfCerainGuest, maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("Order maintenance", orderMaintenance, maintenanceMenu));
        maintenanceMenu.addMenuItem(new MenuItem("Sort maintenances by", () -> { }, createSortMaintenanceMenu()));
        maintenanceMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return maintenanceMenu;
    }

    private Menu createSortRoomMenu() {
        Menu sortRoomMenu = new Menu();
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by price", () -> facade.sortRoomsByPrice(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by capacity", () -> facade.sortRoomsByCapacity(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by rating", () -> facade.sortRoomsByNumberOfStars(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort free rooms by price", () -> facade.sortFreeRoomsByPrice(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort free rooms by capacity", () -> facade.sortFreeRoomsByCapacity(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort free rooms by rating", () -> facade.sortFreeRoomsByNumberOfStars(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem("Sort rooms by release date", () -> facade.sortRoomsByCheckOutDate(), sortRoomMenu));
        sortRoomMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return sortRoomMenu;
    }

    private Menu createSortGuestMenu() {
        Menu sortGuestMenu = new Menu();
        sortGuestMenu.addMenuItem(new MenuItem("Sort guests in alphabetical order", () -> facade.sortGuestsABC(), sortGuestMenu));
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
                () -> facade.sortAllMaintenancesBySectionABC(), sortMaintenanceMenu));
        sortMaintenanceMenu.addMenuItem(new MenuItem("Sort all maintenances by price",
                () -> facade.sortAllMaintenancesByPrice(), sortMaintenanceMenu));
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

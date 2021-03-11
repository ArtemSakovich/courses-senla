package com.company.hotel.clui.actions.room;

import com.company.hotel.clui.api.IAction;
import com.company.hotel.clui.facade.Facade;
import com.company.model.RoomStatus;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeRoomStatus implements IAction {
    @Override
    public void execute() {
        String newRoomStatus;
        Logger log = Logger.getLogger(ChangeRoomStatus.class.getName());

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter room id you want to change status: ");
        Long id = Long.parseLong(input.nextLine());
        System.out.print("Please enter new room status [FREE(f) / OCCUPIED(o) / SERVICED (s) / REPAIRED (r)]: ");
        newRoomStatus = input.nextLine().toLowerCase();
        switch (newRoomStatus) {
            case "f" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.FREE);
            case "o" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.OCCUPIED);
            case "s" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.SERVICED);
            case "r" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.REPAIRED);
            default -> {
                log.log(Level.SEVERE, "Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Incorrect input!");
            }
        }
        System.out.println("Room status has been changed");
    }
}
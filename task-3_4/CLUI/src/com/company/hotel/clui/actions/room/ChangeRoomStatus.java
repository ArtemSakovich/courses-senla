package com.company.hotel.clui.actions.room;

import com.company.hotel.clui.actions.Facade;
import com.company.hotel.clui.actions.IAction;
import com.company.model.MaintenanceSection;
import com.company.model.RoomStatus;

import java.util.Scanner;

public class ChangeRoomStatus implements IAction {
    @Override
    public void execute() {
        String newRoomStatus;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter room id you want to change status: ");
        Long id = Long.parseLong(input.nextLine());
        System.out.print("Please enter new room status [FREE(f) / OCCUPIED(o) / SERVICED (s) / REPAIRED (r)]: ");
        newRoomStatus = input.nextLine();
        switch (newRoomStatus) {
            case "f" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.FREE);
            case "o" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.OCCUPIED);
            case "s" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.SERVICED);
            case "r" -> Facade.getInstance().changeRoomStatus(id, RoomStatus.REPAIRED);
            default -> System.out.println("Incorrect input!");
        }
        System.out.println("Room status has been changed");
    }
}
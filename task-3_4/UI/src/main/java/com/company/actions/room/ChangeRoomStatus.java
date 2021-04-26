package com.company.actions.room;

import com.company.api.IAction;
import com.company.facade.Facade;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;
import com.company.model.RoomStatus;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
@DependencyClass
public class ChangeRoomStatus implements IAction {
    @DependencyComponent
    private Facade facade;

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
            case "f" -> facade.changeRoomStatus(id, RoomStatus.FREE);
            case "o" -> facade.changeRoomStatus(id, RoomStatus.OCCUPIED);
            case "s" -> facade.changeRoomStatus(id, RoomStatus.SERVICED);
            case "r" -> facade.changeRoomStatus(id, RoomStatus.REPAIRED);
            default -> {
                log.log(Level.SEVERE, "Incorrect input when trying to change room status");
                throw new IllegalArgumentException("Incorrect input!");
            }
        }
        System.out.println("Room status has been changed");
    }
}
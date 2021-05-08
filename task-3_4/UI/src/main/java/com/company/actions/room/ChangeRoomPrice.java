package com.company.actions.room;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ChangeRoomPrice implements IAction {
    @Autowired
    private Facade facade;

    @Override
    public void execute() {
        Double price;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter id of room you want to change price: ");
        Long roomId = Long.parseLong(input.nextLine());
        System.out.print("Please enter new price of this room: ");
        Double newRoomPrice = Double.parseDouble(input.nextLine());
        facade.changeRoomPrice(roomId, newRoomPrice);
        System.out.println("Price has been changed");
    }
}

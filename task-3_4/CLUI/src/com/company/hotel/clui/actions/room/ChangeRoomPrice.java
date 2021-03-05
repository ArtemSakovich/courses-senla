package com.company.hotel.clui.actions.room;

import com.company.hotel.clui.actions.Facade;
import com.company.hotel.clui.actions.IAction;

import java.util.Scanner;

public class ChangeRoomPrice implements IAction {
    @Override
    public void execute() {
        Double price;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter id of room you want to change price: ");
        Long roomId = Long.parseLong(input.nextLine());
        System.out.print("Please enter new price of this room: ");
        Double newRoomPrice = Double.parseDouble(input.nextLine());
        Facade.getInstance().changeRoomPrice(roomId, newRoomPrice);
        System.out.println("Price has been changed");
    }
}

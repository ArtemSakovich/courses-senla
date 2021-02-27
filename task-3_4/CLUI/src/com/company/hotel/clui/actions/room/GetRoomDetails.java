package com.company.hotel.clui.actions.room;

import com.company.hotel.clui.actions.Facade;
import com.company.hotel.clui.actions.IAction;

import java.util.Scanner;

public class GetRoomDetails implements IAction {
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter room id you want to see details: ");
        Long roomId = Long.parseLong(input.nextLine());
        System.out.println(Facade.getInstance().viewRoomDetails(roomId));
    }
}

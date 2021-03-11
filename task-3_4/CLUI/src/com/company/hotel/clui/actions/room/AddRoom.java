package com.company.hotel.clui.actions.room;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class AddRoom implements IAction {
    @Override
    public void execute() {
        Integer roomNumber;
        Double roomPrice;
        Integer numberOfBeds;
        Integer numberOfStars;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter room number: ");
        roomNumber = Integer.parseInt(input.nextLine());
        System.out.print("Please enter room price: ");
        roomPrice = Double.parseDouble(input.nextLine());
        System.out.print("Please enter number of beds: ");
        numberOfBeds = Integer.parseInt(input.nextLine());
        System.out.print("Please enter number of stars: ");
        numberOfStars = Integer.parseInt(input.nextLine());
        System.out.println("Room has been added: " + Facade.getInstance().addRoom(roomNumber, roomPrice, numberOfBeds, numberOfStars));
    }
}

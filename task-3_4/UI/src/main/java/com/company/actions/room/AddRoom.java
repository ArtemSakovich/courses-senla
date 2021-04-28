package com.company.actions.room;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class AddRoom implements IAction {
    @DependencyComponent
    private Facade facade;

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
        System.out.println("Room has been added: " + facade.addRoom(numberOfBeds, numberOfStars, roomNumber, roomPrice));
    }
}

package com.company.hotel.clui.actions.guest;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class AddGuest implements IAction {
    @Override
    public void execute() {
        String name;
        String surname;
        Integer age;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter name: ");
        name = input.nextLine();
        System.out.print("Please enter surname: ");
        surname = input.nextLine();
        System.out.print("Please enter age: ");
        age = Integer.parseInt(input.nextLine());
        System.out.println("Guest has been added: " + Facade.getInstance().addGuest(name, surname, age));
    }
}

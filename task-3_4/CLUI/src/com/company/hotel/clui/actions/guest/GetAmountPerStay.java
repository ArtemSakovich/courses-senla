package com.company.hotel.clui.actions.guest;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class GetAmountPerStay implements IAction {
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id: ");
        Long guestId = Long.parseLong(input.nextLine());
        System.out.println("Amount of guest per stay is " + Facade.getInstance().getAmountPerStay(guestId));
    }
}

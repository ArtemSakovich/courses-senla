package com.company.hotel.clui.actions.maintenance;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class ViewListOfMaintenancesOfCerainGuest implements IAction {
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id to see maintenances: ");
        Long guestId = Long.parseLong(input.nextLine());
        Facade.getInstance().viewListOfMaintenancesOfCertainGuest(guestId);
    }
}
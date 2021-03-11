package com.company.hotel.clui.actions.maintenance;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class OrderMaintenance implements IAction {
    @Override
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter id of guest you want to order maintenance: ");
        Long guestId = Long.parseLong(input.nextLine());
        System.out.print("Please enter id of maintenance you want to order: ");
        Long maintenanceIdId = Long.parseLong(input.nextLine());
        Facade.getInstance().orderMaintenance(guestId, maintenanceIdId);
        System.out.println("Maintenance has been ordered");
    }
}

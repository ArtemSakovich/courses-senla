package com.company.hotel.clui.actions.maintenance;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class ChangeMaintenancePrice implements IAction {
    @Override
    public void execute() {
        Double price;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter id of maintenance you want to change price: ");
        Long maintenanceId = Long.parseLong(input.nextLine());
        System.out.print("Please enter new price of this maintenance: ");
        Double newMaintenancePrice = Double.parseDouble(input.nextLine());
        Facade.getInstance().changeMaintenancePrice(maintenanceId, newMaintenancePrice);
        System.out.println("Price has been changed");
    }
}

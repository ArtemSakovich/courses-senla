package com.company.actions.maintenance;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class OrderMaintenance implements IAction {
    @DependencyComponent
    private Facade facade;

    @Override
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter id of guest you want to order maintenance: ");
        Long guestId = Long.parseLong(input.nextLine());
        System.out.print("Please enter id of maintenance you want to order: ");
        Long maintenanceIdId = Long.parseLong(input.nextLine());
        facade.orderMaintenance(guestId, maintenanceIdId);
        System.out.println("Maintenance has been ordered");
    }
}

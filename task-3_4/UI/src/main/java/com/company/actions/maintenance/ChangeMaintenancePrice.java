package com.company.actions.maintenance;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ChangeMaintenancePrice implements IAction {
    @Autowired
    private Facade facade;

    @Override
    public void execute() {
        Double price;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter id of maintenance you want to change price: ");
        Long maintenanceId = Long.parseLong(input.nextLine());
        System.out.print("Please enter new price of this maintenance: ");
        Double newMaintenancePrice = Double.parseDouble(input.nextLine());
        facade.changeMaintenancePrice(maintenanceId, newMaintenancePrice);
        System.out.println("Price has been changed");
    }
}

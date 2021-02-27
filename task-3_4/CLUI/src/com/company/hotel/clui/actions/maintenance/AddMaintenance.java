package com.company.hotel.clui.actions.maintenance;

import com.company.hotel.clui.actions.Facade;
import com.company.hotel.clui.actions.IAction;
import com.company.model.MaintenanceSection;

import java.util.Scanner;

public class AddMaintenance implements IAction {
    @Override
    public void execute() {
        String name;
        Double price;
        String maintenanceSection;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter name: ");
        name = input.nextLine();
        System.out.print("Please enter price: ");
        price = Double.parseDouble(input.nextLine());
        System.out.print("Please enter section [MEDICAL(m) / REPAIR(r) / CLEANING(c) / FOOD (f): ");
        maintenanceSection = input.nextLine();
        switch (maintenanceSection) {
            case "m" -> System.out.println(addingMessage() + Facade.getInstance().addMaintenance(name, price, MaintenanceSection.MEDICAL));
            case "r" -> System.out.println(addingMessage() + Facade.getInstance().addMaintenance(name, price, MaintenanceSection.REPAIR));
            case "c" -> System.out.println(addingMessage() + Facade.getInstance().addMaintenance(name, price, MaintenanceSection.CLEANING));
            case "f" -> System.out.println(addingMessage() + Facade.getInstance().addMaintenance(name, price, MaintenanceSection.FOOD));
            default -> System.out.println("Incorrect input!");
        }
    }

    public String addingMessage() {
       return "Maintenance has been added: ";
    }
}
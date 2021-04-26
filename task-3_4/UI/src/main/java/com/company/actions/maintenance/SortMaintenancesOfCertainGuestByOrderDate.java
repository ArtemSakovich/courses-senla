package com.company.actions.maintenance;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class SortMaintenancesOfCertainGuestByOrderDate implements IAction {
    @DependencyComponent
    private Facade facade;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id you want to sort maintenances: ");
        Long guestId = Long.parseLong(input.nextLine());
        facade.sortMaintenancesOfCertainGuestByOrderDate(guestId);
    }
}

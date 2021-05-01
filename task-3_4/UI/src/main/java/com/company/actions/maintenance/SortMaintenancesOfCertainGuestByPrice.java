package com.company.actions.maintenance;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class SortMaintenancesOfCertainGuestByPrice implements IAction {
    @Autowired
    private Facade facade;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id you want to sort maintenances: ");
        Long guestId = Long.parseLong(input.nextLine());
        facade.sortMaintenancesOfCertainGuestByPrice(guestId);
    }
}

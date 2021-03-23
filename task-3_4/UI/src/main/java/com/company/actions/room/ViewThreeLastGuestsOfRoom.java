package com.company.actions.room;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class ViewThreeLastGuestsOfRoom implements IAction {
    @DependencyComponent
    private Facade facade;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter room id you want to check last guests: ");
        Long roomId = Long.parseLong(input.nextLine());
        facade.getThreeLastGuests(roomId);
    }
}

package com.company.actions.guest;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class GetAmountPerStay implements IAction {
    @DependencyComponent
    private Facade facade;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id: ");
        Long guestId = Long.parseLong(input.nextLine());
        System.out.println("Amount of guest per stay is " + facade.getAmountPerStay(guestId));
    }
}

package com.company.actions.guest;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class GetAmountPerStay implements IAction {
    @Autowired
    private Facade facade;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id: ");
        Long guestId = Long.parseLong(input.nextLine());
        System.out.println("Amount of guest per stay is " + facade.getAmountPerStay(guestId));
    }
}

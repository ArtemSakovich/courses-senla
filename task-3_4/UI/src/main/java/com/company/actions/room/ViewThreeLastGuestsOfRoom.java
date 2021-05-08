package com.company.actions.room;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ViewThreeLastGuestsOfRoom implements IAction {
    @Autowired
    private Facade facade;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter room id you want to check last guests: ");
        Long roomId = Long.parseLong(input.nextLine());
        facade.getThreeLastGuests(roomId);
    }
}

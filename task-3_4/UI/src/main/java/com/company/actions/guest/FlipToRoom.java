package com.company.actions.guest;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.time.LocalDateTime;
import java.util.Scanner;
@DependencyClass
public class FlipToRoom implements IAction {
    @DependencyComponent
    private Facade facade;
    @Override
    public void execute() {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter guest id to check into: ");
            Long guestId = Long.parseLong(input.nextLine());
            System.out.print("Please enter room id to check into: ");
            Long roomId = Long.parseLong(input.nextLine());
            System.out.print("Please enter number of days of stay: ");
            Long days = Long.parseLong(input.nextLine());
            facade.accommodateToRoom(guestId, roomId, LocalDateTime.now().plusDays(days));
            System.out.println("Guest has been accommodated to room");
    }
}

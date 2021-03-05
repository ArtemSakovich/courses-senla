package com.company.hotel.clui.actions.guest;

import com.company.hotel.clui.actions.Facade;
import com.company.hotel.clui.actions.IAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FlipToRoom implements IAction {
    @Override
    public void execute() {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter guest id to check into: ");
            Long guestId = Long.parseLong(input.nextLine());
            System.out.print("Please enter room id to check into: ");
            Long roomId = Long.parseLong(input.nextLine());
            System.out.print("Please enter number of days of stay: ");
            Long days = Long.parseLong(input.nextLine());
            Facade.getInstance().accommodateToRoom(guestId, roomId, LocalDateTime.now().plusDays(days));
            System.out.println("Guest has been accommodated to room");
    }
}

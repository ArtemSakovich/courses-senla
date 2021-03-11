package com.company.hotel.clui.actions.room;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GetFreeRoomsByDate implements IAction {
    @Override
    public void execute() {
        String dateToCheck;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter date [M-d-yyyy]: ");
        dateToCheck = input.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M-d-yyyy");
        LocalDateTime date = LocalDateTime.parse(dateToCheck, dateFormat);
        Facade.getInstance().getFreeRoomsByDate(date);
    }
}

package com.company.actions.room;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@DependencyClass
public class GetFreeRoomsByDate implements IAction {
    @DependencyComponent
    private Facade facade;

    @Override
    public void execute() {
        String dateToCheck;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter date [M-d-yyyy]: ");
        dateToCheck = input.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M-d-yyyy");
        LocalDateTime date = LocalDateTime.parse(dateToCheck, dateFormat);
        facade.getFreeRoomsByDate(date);
    }
}

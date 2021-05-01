package com.company.actions.room;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@Component
public class GetFreeRoomsByDate implements IAction {
    @Autowired
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

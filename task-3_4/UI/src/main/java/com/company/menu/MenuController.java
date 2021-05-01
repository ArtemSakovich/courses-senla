package com.company.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class MenuController {
    @Autowired
    private Builder builder;
    @Autowired
    private Navigator navigator;

    private MenuController() {

    }

    public void run() {
        Scanner input = new Scanner(System.in);
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -2;

        while (!index.equals(-1)) {
            index = input.nextInt();
            navigator.navigate(index);
            navigator.printMenu();
        }
    }
}

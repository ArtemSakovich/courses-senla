package com.company.menu;

import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class MenuController {
    @DependencyComponent
    private Builder builder;
    @DependencyComponent
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

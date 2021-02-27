package com.company;

import com.company.hotel.clui.actions.Facade;
import com.company.hotel.clui.menu.MenuController;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("logging.properties"));
        Facade.getInstance().createTestData();
        MenuController.getInstance().run();
    }
}

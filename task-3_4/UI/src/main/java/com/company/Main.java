package com.company;

import com.company.menu.MenuController;
import com.company.util.ApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.LogManager;


public class Main {
    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.properties"));
        //Facade.getInstance().createTestData();
        HashMap<String, Object> applicationContext = (HashMap<String, Object>) ApplicationContext.getApplicationContext(MenuController.class);
        MenuController menuController = (MenuController) applicationContext.get(MenuController.class.getName());
        menuController.run();
    }
}

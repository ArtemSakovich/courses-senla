package com.company.menu;

import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.logging.Level;
import java.util.logging.Logger;
@DependencyClass
public class Navigator {
    @DependencyComponent
    private Menu currentMenu;
    @DependencyComponent
    private Builder builder;
    private static final Logger log = Logger.getLogger(Navigator.class.getName());

    private Navigator() {

    }

    public void printMenu() {
        System.out.println("");
        System.out.println("### Please select an action ###");
        int currentMenuItemIndex = 0;
        for (MenuItem menuItem : currentMenu.getMenuItems()) {
            System.out.println(currentMenuItemIndex + ": " + menuItem.getTitle());
            currentMenuItemIndex++;
        }
    }

    public void navigate(int index) {
        if (currentMenu != null) {
            try {
                MenuItem menuItem = currentMenu.getMenuItems().get(index);
                try {
                    menuItem.doAction();
                    currentMenu = menuItem.getNextMenu();
                } catch (NumberFormatException ex) {
                    System.out.println("Incorrect input!");
                    currentMenu = builder.createRetryMenu(menuItem);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    log.log(Level.SEVERE, e.getMessage(), e);
                    currentMenu = builder.createRetryMenu(menuItem);

                }
            } catch (IndexOutOfBoundsException e) {
                // User selected non-existing menu index
                System.out.println("Incorrect input!");
            }
        }
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}

package com.company.hotel.clui.menu;

public class Navigator {
    private static Navigator instance;
    private Menu currentMenu;
    private Builder builder;

    private Navigator() {
        builder = Builder.getInstance();
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
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

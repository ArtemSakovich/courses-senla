package com.company.hotel.clui.menu;

import com.company.api.exceptions.OperationCancelledException;
import com.company.hotel.clui.api.IAction;

import java.text.ParseException;

public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() throws ParseException, OperationCancelledException{
        action.execute();
    }

    public IAction getAction() {
        return action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public String getTitle() {
        return title;
    }
}

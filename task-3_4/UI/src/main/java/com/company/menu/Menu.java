package com.company.menu;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Menu {
    private String name;
    private List<MenuItem> menuItems = new ArrayList<>();

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }
}

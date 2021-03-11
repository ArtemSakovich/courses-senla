package com.company.hotel.clui.actions.guest;

import com.company.hotel.clui.facade.Facade;
import com.company.hotel.clui.api.IAction;

import java.util.Scanner;

public class EvictFromRoom implements IAction {
    @Override
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id to evict: ");
        Long id = Long.parseLong(input.nextLine());
        Facade.getInstance().evictFromRoom(id);
        System.out.print("Guest has been evicted");
    }
}
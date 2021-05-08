package com.company.actions.guest;

import com.company.facade.Facade;
import com.company.api.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class EvictFromRoom implements IAction {
    @Autowired
    private Facade facade;
    /**
     * Method calls when the user wants to evict a guest from a room. Method ask the user to enter a guest id through
     * the console. After that the method of the same name from the Facade will be called, to which the guest ID will
     * be passed as a parameter. If such an ID does not exist, then the Facade method will throw an
     * IllegalArgumentException and control will no longer return to the execute() method. In case an existing ID was
     * entered the method from the Facade will work without any exceptions and control will return to the execute()
     * method, which will print a message to the console about the successful eviction of the guest.
     */
    @Override
    public void execute(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter guest id to evict: ");
        Long id = Long.parseLong(input.nextLine());
        facade.evictFromRoom(id);
        System.out.print("Guest has been evicted");
    }
}
package com.company.actions.guest;

import com.company.facade.Facade;
import com.company.api.IAction;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.util.Scanner;
@DependencyClass
public class AddGuest implements IAction {
    @DependencyComponent
    private Facade facade;

    /**
     * Method calls when the user wants to add a guest to the database. Method ask the user to enter a guest name,
     * guest lastname, and guest age through the console. If the user enters an incorrect age, the method will throw
     * a NumberFormatException and finish. If the input is correct, the method will print to console a message about
     * the successful addition of the guest, as well as call the method of the same name from the Facade,
     * passing the input parameters to it. And that, in turn, will call the method from GuestService, also passing the
     * parameters. The GuestService method will return the object of the added guest to the Facade method, otherwise
     * it will return it to this method and information about the new guest will be printed to the console.
     */
    @Override
    public void execute() {
        String name;
        String surname;
        Integer age;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter name: ");
        name = input.nextLine();
        System.out.print("Please enter surname: ");
        surname = input.nextLine();
        System.out.print("Please enter age: ");
        age = Integer.parseInt(input.nextLine());
        System.out.println("Guest has been added: " + facade.addGuest(name, surname, age));
    }
}

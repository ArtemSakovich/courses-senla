package com.company;

import java.util.ArrayList;

public class Car implements IProduct{
    private IProductPart engine;
    private IProductPart body;
    private IProductPart chassis;

    public IProductPart getEngine() {
        return engine;
    }

    public IProductPart getBody() {
        return body;
    }

    public IProductPart getChassis() {
        return chassis;
    }

    @Override
    public void installParts(IProductPart firstPart, IProductPart secondPart, IProductPart thirdPart) {
        ArrayList<IProductPart> productParts = new ArrayList<>();
        productParts.add(firstPart);
        productParts.add(secondPart);
        productParts.add(thirdPart);
    /*
        checking which part the object is
    */
        for (IProductPart productPart : productParts) {
            if (productPart instanceof CarBody) {
                this.body = productPart;
                System.out.println("Car body has been installed");
            }
            if (productPart instanceof Engine) {
                this.engine = productPart;
                System.out.println("Engine has been installed");
            }
            if (productPart instanceof VehicleChassis) {
                this.chassis = productPart;
                System.out.println("Vehicle chassis has been installed");
            }
        }
    }
}

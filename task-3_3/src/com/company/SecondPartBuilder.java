package com.company;

public class SecondPartBuilder implements ILineStep{
    @Override
    public IProductPart buildProductPart() {
        IProductPart vehicleChassis = new VehicleChassis(7000, 4500.55);
        System.out.println("Vehicle chassis has been made");
        return vehicleChassis;
    }
}

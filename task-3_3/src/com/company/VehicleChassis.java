package com.company;

public class VehicleChassis implements IProductPart{
    private int liftingCapacity;
    private double length;

    public VehicleChassis(int liftingCapacity, double length) {
        this.liftingCapacity = liftingCapacity;
        this.length = length;
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }

    public double getLength() {
        return length;
    }

    public void setLiftingCapacity(int liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    public void setLength(double length) {
        this.length = length;
    }
}

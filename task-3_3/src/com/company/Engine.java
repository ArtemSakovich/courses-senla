package com.company;

public class Engine implements IProductPart{
    private double volume;
    private int horsePower;

    public Engine(double volume, int horsePower) {
        this.volume = volume;
        this.horsePower = horsePower;
    }

    public double getVolume() {
        return volume;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}

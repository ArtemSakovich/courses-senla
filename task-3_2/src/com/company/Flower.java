package com.company;

public abstract class Flower {
    private String flowerName;
    private double price;

    public Flower(String flowerName, double price) {
        this.flowerName = flowerName;
        this.price = price;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public double getPrice() {
        return price;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return String.format("Flower: %10s \t\t\t Price: %15g", flowerName, price);
    }
}
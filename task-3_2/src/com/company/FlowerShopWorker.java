package com.company;

public class FlowerShopWorker {

    private FlowerPack flowerPack;

    private FlowerPack getFlowerPackInstance() {
        if (flowerPack == null) {
            flowerPack = new FlowerPack();
        }
        return flowerPack;
    }

    public void resetFlowerPack() {
        flowerPack = new FlowerPack();

    }

    public void addFlower(Flower flower) {
        getFlowerPackInstance().getFlowerPackContainer().add(flower);
    }

    public void printFlowerPackSummary() {
        for (Flower flower : getFlowerPackInstance().getFlowerPackContainer()) {
            System.out.println(flower.toString());
        }
        System.out.println("Total flower pack's price: " + getTotalFlowerPackPrice());
    }

    private double getTotalFlowerPackPrice() {
        double totalPrice = 0;
        for (Flower flower : getFlowerPackInstance().getFlowerPackContainer()) {
            totalPrice += flower.getPrice();
        }
        return totalPrice;
    }
}
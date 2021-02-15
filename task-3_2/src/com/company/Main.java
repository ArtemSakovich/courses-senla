package com.company;

/*
   The program contains hierarchies of flowers for a flower shop.
   The program provides an opportunity to collect a bouquet of flowers and determine its cost.
 */

public class Main {

    public static void main(String[] args) {
       FlowerShopWorker worker = new FlowerShopWorker();
       worker.resetFlowerPack();

        Flower lily = new Lily("Lily", 123.12);
        Flower rose = new Rose("Rose", 107.83);
        Flower narcissus = new Narcissus("Narcissus", 144.56);

        worker.addFlower(lily);
        worker.addFlower(rose);
        worker.addFlower(narcissus);

        worker.printFlowerPackSummary();
    }
}

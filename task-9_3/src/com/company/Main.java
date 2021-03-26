package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Double> sharedQueue = new LinkedList<>();
        int size = 4;
        Thread prodThread = new Thread(new ThreadProducer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(new ThreadConsumer(sharedQueue), "Consumer");
        prodThread.start();
        consThread.start();
    }
}
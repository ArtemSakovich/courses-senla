package com.company;

import java.util.Queue;

public class ThreadProducer implements Runnable{
    private final Queue<Double> sharedQueue;
    private final int SIZE;

    public ThreadProducer(Queue<Double> sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Produced: " + produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private double produce() throws InterruptedException {
        synchronized (sharedQueue) {
            if (sharedQueue.size() == SIZE) {
                sharedQueue.wait();
            }

            double newValue = Math.random();
            sharedQueue.add(newValue);

            sharedQueue.notifyAll();

            return newValue;
        }
    }
}

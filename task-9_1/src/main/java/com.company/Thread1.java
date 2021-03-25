package com.company;

import static java.lang.Thread.sleep;

public class Thread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("thread1 state after .start(): " + Thread.currentThread().getState()); //RUNNABLE
        try {
            sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
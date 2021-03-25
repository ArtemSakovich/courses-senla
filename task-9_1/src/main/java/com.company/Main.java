package com.company;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        try {
            Thread thread1 = new Thread(new Thread1());
            Thread thread2 = new Thread(new Thread2());
            System.out.println("thread1 state after creating: " + thread1.getState()); //NEW
            thread1.start();
            thread2.start();
            sleep(500);
            System.out.println("thread1 state after .sleep(): " + thread1.getState()); //TIMED_WAITING
            System.out.println("thread2 state after .wait(): " + thread2.getState()); //TIMED_WAITING
            Object a = Thread2.getA();

            synchronized (a) {
                sleep(2000);
                a.notify();
            }

            System.out.println("thread2 state after synchronized block: " + thread2.getState()); //BLOCKED
            sleep(2000);
            System.out.println("thread2 state after .sleep(): " + thread2.getState()); //TERMINATED
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
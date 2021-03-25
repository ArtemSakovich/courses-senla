package com.company;

public class Thread2 implements Runnable {
    private static Object a = new Object();

    @Override
    public void run() {
        try {

            synchronized (a) {
                a.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getA() {
        return a;
    }
}
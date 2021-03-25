package com.company;

public class Main {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread #1");
        MyThread thread2 = new MyThread("Thread #2");

        thread1.start();
        thread2.start();
    }
}

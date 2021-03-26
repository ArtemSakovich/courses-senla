package com.company;
public class Main {
    public static void main(String[] strings) {
        Object lock = new Object();
        new MyThread(lock, "Thread #1").start();
        new MyThread(lock, "Thread #2").start();
    }
}
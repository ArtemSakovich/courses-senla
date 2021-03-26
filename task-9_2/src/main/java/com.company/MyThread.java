package com.company;

class MyThread extends Thread {

    private Object lock;
    private String threadName;

    public MyThread(Object object, String threadName) {
        this.lock = object;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    System.out.println(threadName);
                    lock.notify();
                    lock.wait();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


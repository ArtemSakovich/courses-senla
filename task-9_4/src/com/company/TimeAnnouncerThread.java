package com.company;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

public class TimeAnnouncerThread implements Runnable{
    private Long numberOfMilliseconds;

    public TimeAnnouncerThread(Integer numberOfSeconds) {
        numberOfMilliseconds = numberOfSeconds * 1000L;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Current time: " + LocalDateTime.now().getHour() + ":" +
                        LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond());
                sleep(numberOfMilliseconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

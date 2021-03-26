package com.company;

public class Main {

    public static void main(String[] args) {
	TimeAnnouncerThread timeAnnouncerThread = new TimeAnnouncerThread(2);
	Thread thread = new Thread(timeAnnouncerThread);
	thread.start();
    }
}

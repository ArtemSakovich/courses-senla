package com.company;

/*
  The program displays a randomly generated three-digit natural number and the sum of its digits
*/

public class RandomNumberTask {

    public static void main(String[] args) {

        int randomNumber = 0;

        while (randomNumber / 100 == 0) {
            randomNumber = (new java.util.Random()).nextInt(999);
        }
        int sum = 0;

        System.out.println("Random number: " + randomNumber);

        while (randomNumber != 0) {
            sum += randomNumber % 10;
            randomNumber /= 10;
        }

        System.out.println("Sum of digits in this number: " + sum);
    }
}

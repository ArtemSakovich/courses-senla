package com.company;

/*
  Программа выводит на экран случайно сгенерированное трёхзначное натуральное число и сумму его цифр
*/

public class RandomNumberTask {

    public static void main(String[] args) {

        int randomNumber = (new java.util.Random()).nextInt(999);;
        int sum = 0;

        System.out.println("Random number: " + randomNumber);

        while (randomNumber != 0) {
            sum += randomNumber % 10;
            randomNumber /= 10;
        }

        System.out.println("Sum of digits in this number: " + sum);
    }
}

package com.example;

public class AccountNumberGenerator {
    private static long number = 0;

    public static long generateNumber() {
        return ++number;
    }
}

package com.example;

public class AccountNumberGenerator {
    private static long number = 100_000_000;

    public static long generateNumber() {
        return ++number;
    }
}

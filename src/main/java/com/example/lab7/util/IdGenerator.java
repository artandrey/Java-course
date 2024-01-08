package com.example.lab7.util;

public class IdGenerator {
    private static int id = 0;

    public static int generateId() {
        return ++id;
    }

    public static void reset() {
        id = 0;
    }
}

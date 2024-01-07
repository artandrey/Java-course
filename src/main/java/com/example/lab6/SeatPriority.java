package com.example.lab6;

public enum SeatPriority {
    NONE(0), LOW(1), HIGH(2);

    private final int code;

    private SeatPriority(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }

    public String toString() {
        return String.valueOf(code);
    }
}

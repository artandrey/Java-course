package com.example;

public enum SeatStatus {
    FREE(0), BOOKED(1);

    private final int code;

    private SeatStatus(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }

    public String toString() {
        return String.valueOf(code);
    }
}

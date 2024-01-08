package com.example.lab6;

public interface ITheater {
    int getSeatsCount();

    int getRowsCount();

    int getHallsCount();

    void bookSeats(int hallNumber, int row, int[] seats);

    void cancelBooking(int hallNumber, int row, int[] seats);

    boolean checkAvailability(int hallNumber, int row, int numSeats);

    void printSeatingArrangement(int hallNumber);

    SeatRange findBestAvailable(int hallNumber, int numSeats);

    void autoBook(int hallNumber, int numSeats);
}

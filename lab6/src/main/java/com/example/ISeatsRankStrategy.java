package com.example;

public interface ISeatsRankStrategy {
    public SeatPriority rank(int rowIndex, int seatIndex, SeatStatus seatStatus, ITheater theater);
}

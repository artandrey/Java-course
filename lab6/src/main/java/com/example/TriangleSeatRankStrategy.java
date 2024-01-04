package com.example;

public class TriangleSeatRankStrategy implements ISeatsRankStrategy {
    @Override
    public SeatPriority rank(int rowIndex, int seatIndex, SeatStatus seatStatus, ITheater theater) {
        if (seatStatus == SeatStatus.BOOKED) {
            return SeatPriority.NONE;
        }
        int seatsCount = theater.getSeatsCount();
        int highestRankPlacesCount = seatsCount / 2;
        int startHighestRankIndex = Math.max(highestRankPlacesCount / 2 - rowIndex, 0);
        int endHighestRankIndex = Math.min(seatsCount - highestRankPlacesCount / 2 + rowIndex - 1, seatsCount - 1);
        if (seatIndex >= startHighestRankIndex && seatIndex <= endHighestRankIndex) {
            return SeatPriority.HIGH;
        } else {
            return SeatPriority.LOW;
        }

    }
}

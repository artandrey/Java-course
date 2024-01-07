package com.example.lab6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import lombok.Getter;

public class Theater implements ITheater {
    @Getter
    private final int hallsCount;
    @Getter
    private final int rowsCount;
    @Getter
    private final int seatsCount;

    private final ISeatsRankStrategy seatsRankStrategy;

    private final SeatStatus[][][] theaterStore;

    public Theater(int hallsCount, int rowsCount, int seatsCount, ISeatsRankStrategy seatsRankStrategy) {
        this.hallsCount = hallsCount;
        this.rowsCount = rowsCount;
        this.seatsCount = seatsCount;
        this.seatsRankStrategy = seatsRankStrategy;
        theaterStore = new SeatStatus[hallsCount][rowsCount][seatsCount];
        Arrays.stream(theaterStore).forEach(hall -> Arrays.stream(hall).forEach(row -> Arrays.fill(row, SeatStatus.FREE)));
    }

    private void setSeatStatus(int hallNumber, int row, int[] seats, SeatStatus status) {
        Arrays.stream(seats).forEach(seat -> setSeatStatus(hallNumber, row, seat, status));
    }

    private void setSeatStatus(int hallNumber, int row, int seat, SeatStatus status) {
        theaterStore[hallNumber][row][seat] = status;
    }

    private SeatStatus getSeatStatus(int hallNumber, int row, int seat) {
        return theaterStore[hallNumber][row][seat];
    }

    public void bookSeats(int hallNumber, int row, int[] seats) {
        setSeatStatus(hallNumber, row, seats, SeatStatus.BOOKED);
    }

    public void cancelBooking(int hallNumber, int row, int[] seats) {
        setSeatStatus(hallNumber, row, seats, SeatStatus.FREE);
    }

    public boolean checkAvailability(int hallNumber, int row, int numSeats) {
        return getSeatStatus(hallNumber, row, numSeats) == SeatStatus.FREE;
    }

    public void printSeatingArrangement(int hallNumber) {
        StringBuilder seatingArrangement = new StringBuilder();
        seatingArrangement.append("    ");
        IntStream.range(0, seatsCount).mapToObj(seat -> String.format("%3d", seat + 1)).forEach(seatingArrangement::append);
        seatingArrangement.append(System.lineSeparator());
        IntStream.range(0, rowsCount).forEach(row -> {
            seatingArrangement.append(String.format("%2d |", row + 1));
            IntStream.range(0, seatsCount).mapToObj(seat -> String.format("%3d", getSeatStatus(hallNumber, row, seat).toInt())).forEach(seatingArrangement::append);
            seatingArrangement.append(System.lineSeparator());
        });

        System.out.println(seatingArrangement.toString());
    }

    public SeatRange findBestAvailable(int hallNumber, int numSeats) {
        SeatPriority[][] rankedSeats = getRankedSeats(hallNumber);
        return IntStream.range(0, rowsCount).boxed().flatMap(row -> IntStream.range(0, seatsCount - numSeats).mapToObj(startSeat -> {
            int rank = IntStream.range(0, numSeats).map(seat -> {
                SeatPriority seatPriority = rankedSeats[row][startSeat + seat];
                return (seatPriority == SeatPriority.NONE) ? 0 : seatPriority.toInt();
            }).sum();

            return new SeatRange(row, startSeat, numSeats, rank);
        })).max(Comparator.comparingInt(SeatRange::getRank)).orElse(null);
    }

    private SeatPriority[][] getRankedSeats(int hallNumber) {
        SeatStatus[][] hall = theaterStore[hallNumber];
        return IntStream.range(0, rowsCount)
                .mapToObj(rowIndex -> IntStream.range(0, seatsCount).mapToObj(seatIndex -> rankSeat(rowIndex, seatIndex, hall[rowIndex][seatIndex])).toArray(SeatPriority[]::new))
                .toArray(SeatPriority[][]::new);
    }

    private SeatPriority rankSeat(int rowIndex, int seatIndex, SeatStatus seatStatus) {
        return seatsRankStrategy.rank(rowIndex, seatIndex, seatStatus, this);
    }

    public void autoBook(int hallNumber, int numSeats) {
        SeatRange bestAvailableRange = findBestAvailable(hallNumber, numSeats);
        bookSeats(hallNumber, bestAvailableRange.getRow(), IntStream.range(bestAvailableRange.getStart(), bestAvailableRange.getEnd()).toArray());
    }

}

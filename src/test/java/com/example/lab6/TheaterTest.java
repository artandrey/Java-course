package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TheaterTest {

    private Theater theater;

    @Before
    public void setUp() {
        theater = new Theater(5, 10, 20, new TriangleSeatRankStrategy());
    }

    @Test
    public void testBookSeats() {
        theater.bookSeats(0, 0, new int[] { 0, 1, 2 });
        assertFalse(theater.checkAvailability(0, 0, 0));
        assertFalse(theater.checkAvailability(0, 0, 1));
        assertFalse(theater.checkAvailability(0, 0, 2));
    }

    @Test
    public void testCancelBooking() {
        theater.bookSeats(0, 0, new int[] { 0, 1, 2 });
        theater.cancelBooking(0, 0, new int[] { 0, 1, 2 });
        assertTrue(theater.checkAvailability(0, 0, 0));
        assertTrue(theater.checkAvailability(0, 0, 1));
        assertTrue(theater.checkAvailability(0, 0, 2));
    }

    @Test
    public void testCheckAvailability() {
        assertTrue(theater.checkAvailability(0, 0, 5));
        theater.bookSeats(0, 0, new int[] { 0, 1, 2, 3, 4 });
        assertFalse(theater.checkAvailability(0, 0, 4));
    }

    @Test
    public void testFindBestAvailable() {
        theater.bookSeats(0, 0, new int[] { 0, 1, 2, 3, 4 });
        SeatRange bestAvailable = theater.findBestAvailable(0, 3);
        assertEquals(0, bestAvailable.getRow());
        assertEquals(5, bestAvailable.getStart());
        assertEquals(8, bestAvailable.getEnd());
    }

    @Test
    public void testAutoBook() {
        theater.autoBook(0, 4);
        assertFalse(theater.checkAvailability(0, 0, 5) && theater.checkAvailability(0, 0, 6) && theater.checkAvailability(0, 0, 7) && theater.checkAvailability(0, 0, 8));
    }
}

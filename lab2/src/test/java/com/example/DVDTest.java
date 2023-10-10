package com.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.DVD;

public class DVDTest {
    private DVD dvd;

    @BeforeEach
    public void setUp() {
        dvd = new DVD("Test DVD", 1, 120.5f);
    }

    @Test
    public void testBorrowItem() {
        dvd.borrowItem();
        assertTrue(dvd.isBorrowed());
    }

    @Test
    public void testReturnItem() {
        dvd.borrowItem();
        assertTrue(dvd.isBorrowed());
        dvd.returnItem();
        assertFalse(dvd.isBorrowed());
    }
}

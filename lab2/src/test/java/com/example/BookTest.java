package com.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.Items.Book;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Test Book", "Test Author");
    }

    @Test
    public void testBorrowItem() {

        book.borrowItem();

        assertTrue(book.isBorrowed());
    }

    @Test
    public void testReturnItem() {
        book.borrowItem();
        assertTrue(book.isBorrowed());

        book.returnItem();

        assertFalse(book.isBorrowed());
    }
}
package com.example;

import com.example.exceptions.LibraryException;
import com.example.models.Book;
import com.example.models.Item;
import com.example.models.Patron;
import com.example.services.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private Patron patron;

    @BeforeEach
    public void setUp() {
        library = new Library();
        patron = new Patron("Test Patron", 1);
    }

    @Test
    public void testRegisterPatron() {
        library.registerPatron(patron);
    }

    @Test
    public void testLendItem() throws LibraryException {
        Item item = new Book("Test Book", 1, "Test Author");
        library.addItem(item);
        library.registerPatron(patron);
        library.lendItem(patron, item);
        assertTrue(patron.getBorrowedItems().contains(item));
        assertTrue(item.isBorrowed());
    }

    @Test
    public void testReturnItem() throws LibraryException {
        Item item = new Book("Test Book", 1, "Test Author");
        library.addItem(item);
        library.registerPatron(patron);
        library.lendItem(patron, item);
        library.returnItem(patron, item);
        assertFalse(patron.getBorrowedItems().contains(item));
        assertFalse(item.isBorrowed());
    }

    @Test
    public void testGetAvailable() {
        Item item1 = new Book("Book 1", 1, "Author 1");
        Item item2 = new Book("Book 2", 2, "Author 2");
        library.addItem(item1);
        library.addItem(item2);
        List<Item> availableItems = library.getAvailable();
        assertTrue(availableItems.contains(item1));
        assertTrue(availableItems.contains(item2));
    }
}

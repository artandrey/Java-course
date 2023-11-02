package com.example;

import com.example.exceptions.ServiceException;
import com.example.models.Patron;
import com.example.models.Items.Book;
import com.example.models.Items.DVD;
import com.example.services.Library;

public class App {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Book 1", "Author 1");
        Book book2 = new Book("Book 2", "Author 2");
        DVD dvd1 = new DVD("DVD 1", 120);
        DVD dvd2 = new DVD("DVD 2", 90);

        Patron patron1 = new Patron("Patron 1");
        Patron patron2 = new Patron("Patron 2");

        library.addItem(book1);
        library.addItem(book2);
        library.addItem(dvd1);
        library.addItem(dvd2);

        library.registerPatron(patron1);
        library.registerPatron(patron2);

        System.out.println("\n-----Available items-----\n");
        library.getAvailable().forEach(System.out::println);

        try {
            library.lendItem(patron1, book1);
            library.lendItem(patron2, dvd1);

            System.out.println("\n-----Available items after somebody borrowed-----\n");
            library.getAvailable().forEach(System.out::println);

            library.returnItem(patron1, book1);
            library.returnItem(patron2, dvd2);

            System.out.println("\n-----Available items after return-----\n");
            library.getAvailable().forEach(System.out::println);

        } catch (ServiceException e) {
            System.out.println("Library error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception\n" + e.getMessage());
        }

        library.getAvailable();
    }
}

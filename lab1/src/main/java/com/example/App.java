package com.example;

import com.example.db.repository.DBRepository;
import com.example.models.Author;
import com.example.models.Book;
import com.example.services.LibraryService;

public class App {
    public static void main(String[] args) {
        DBRepository<Author> authorRepository = new DBRepository<>();
        DBRepository<Book> bookRepository = new DBRepository<>();
        LibraryService libraryService = new LibraryService(bookRepository);

        Author author = new Author("J.K. Rowling");
        authorRepository.add(author);

        Book book1 = new Book("Harry Potter and the Sorcerer's Stone", 12345, 1997, author.getId());
        Book book2 = new Book("Harry Potter and the Chamber of Secrets", 23456, 1998, author.getId());
        Book book3 = new Book("Harry Potter and the Prisoner of Azkaban", 34567, 1999, author.getId());

        System.out.println("Adding books");

        libraryService.create(book1);
        libraryService.create(book2);
        libraryService.create(book3);

        System.out.println("Printing list of all books");

        libraryService.getAll().forEach(System.out::println);

        System.out.println("Searching book with title:\n\tHarry Potter and the Sorcerer's Stone");

        var books = libraryService.getByTitle("Harry Potter and the Sorcerer's Stone");

        books.forEach(System.out::println);

    }
}

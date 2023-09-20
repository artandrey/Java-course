package com.example.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.db.repository.DBRepository;
import com.example.models.Author;
import com.example.models.Book;

public class LibraryServiceTest {
    private LibraryService libraryService;
    private DBRepository<Book> bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository = new DBRepository<Book>();
        libraryService = new LibraryService(bookRepository);
        System.out.println(libraryService);
    }

    @Test
    public void testCreate() {
        Author author = new Author("J.K. Rowling");
        DBRepository<Author> authorRepository = new DBRepository<>();
        authorRepository.add(author);

        Book book = new Book("Harry Potter and the Sorcerer's Stone", 12345, 1997, author.getId());

        libraryService.create(book);

        List<Book> books = libraryService.getAll();
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
    }

    @Test
    public void testGetAll() {
        Author author = new Author("J.K. Rowling");
        DBRepository<Author> authorRepository = new DBRepository<>();
        authorRepository.add(author);

        Book book1 = new Book("Book 1", 12345, 1997, author.getId());
        Book book2 = new Book("Book 2", 23456, 1998, author.getId());
        Book book3 = new Book("Book 3", 34567, 1999, author.getId());

        libraryService.create(book1);
        libraryService.create(book2);
        libraryService.create(book3);

        List<Book> books = libraryService.getAll();
        assertEquals(3, books.size());
    }

    @Test
    public void testGetByTitle() {
        Author author = new Author("J.K. Rowling");
        DBRepository<Author> authorRepository = new DBRepository<>();
        authorRepository.add(author);

        Book book1 = new Book("Book 1", 12345, 1997, author.getId());
        Book book2 = new Book("Book 2", 23456, 1998, author.getId());
        Book book3 = new Book("Book 3", 34567, 1999, author.getId());

        libraryService.create(book1);
        libraryService.create(book2);
        libraryService.create(book3);

        List<Book> books = libraryService.getByTitle("Book 1");
        assertEquals(1, books.size());
        assertEquals(book1.getId(), books.get(0).getId());
    }
}

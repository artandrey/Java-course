package com.example.services;

import java.util.List;

import com.example.db.repository.DBRepository;
import com.example.models.Book;

public class LibraryService {
    private final DBRepository<Book> bookRepository;

    public LibraryService(DBRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void create(Book book) {
        bookRepository.add(book);
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public List<Book> getByTitle(String title) {
        return bookRepository.findManyBy((book) -> book.getTitle().equalsIgnoreCase(title));
    }
}

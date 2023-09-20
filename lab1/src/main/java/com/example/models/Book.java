package com.example.models;

import lombok.ToString;

@ToString
public class Book extends Model {
    private String title;
    private long isbn;
    private int publishmentYear;
    private long authorId;

    public Book(String title, long isbn, int publishmentYear, long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.publishmentYear = publishmentYear;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getPublishmentYear() {
        return publishmentYear;
    }

    public void setPublishmentYear(int publishmentYear) {
        this.publishmentYear = publishmentYear;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author author) {
        this.authorId = author.getId();
    }

}

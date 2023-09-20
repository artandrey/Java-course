package com.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
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

}

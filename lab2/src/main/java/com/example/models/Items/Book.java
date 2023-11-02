package com.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Book extends Item {
    private String author;

    public Book(String title, long id, String author) {
        super(title, id);
        this.author = author;
    }
}

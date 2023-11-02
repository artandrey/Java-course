package com.example.models.Items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Book extends Item {
    private String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }
}

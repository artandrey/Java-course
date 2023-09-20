package com.example.models;

import lombok.ToString;

@ToString
public class Author extends Model {
    private String name;

    public Author(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

}

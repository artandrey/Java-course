package com.example.models;

import java.util.List;

import lombok.Data;

@Data
public class Patron implements IIdentifiable {
    private String name;
    private long id;
    private List<Item> borrowedItems;

    public Patron(String name, long id) {
        this.name = name;
        this.id = id;
    }

    void borrowItem(Item item) {

    }

    void returnItem(Item item) {

    }

}

package com.example.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Patron implements IIdentifiable {
    private String name;
    private long id;
    private List<Item> borrowedItems = new ArrayList<>();

    public Patron(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public void borrowItem(Item item) {
        this.borrowedItems.add(item);
        item.borrowItem();
    }

    public void returnItem(Item item) {
        this.borrowedItems.remove(item);
        item.returnItem();
    }

}

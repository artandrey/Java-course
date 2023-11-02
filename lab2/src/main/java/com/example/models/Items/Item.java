package com.example.models;

import lombok.Data;

@Data
public abstract class Item implements IIdentifiable {
    private String title;
    private long id;
    private boolean isBorrowed = false;

    public Item(String title, long id) {
        this.title = title;
        this.id = id;
    }

    public Item(String title, long id, boolean isBorrowed) {
        this.title = title;
        this.id = id;
        this.isBorrowed = isBorrowed;
    }

    public void borrowItem() {
        this.isBorrowed = true;
    }

    public void returnItem() {
        this.isBorrowed = false;
    }

}

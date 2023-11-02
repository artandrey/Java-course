package com.example.models;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Items.Item;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Patron extends BaseModel {
    private String name;
    private List<Item> borrowedItems = new ArrayList<>();

    public Patron(String name) {
        this.name = name;
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

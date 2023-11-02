package com.example.services;

import java.util.List;

import com.example.models.Items.Item;

public interface IManageable {
    void addItem(Item item);

    void removeItem(Item item);

    List<Item> getAvailable();
}

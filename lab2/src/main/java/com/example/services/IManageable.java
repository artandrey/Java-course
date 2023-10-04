package com.example.services;

import java.util.List;

import com.example.models.Item;

public interface IManageable {
    void addItem(Item item);

    void removeItem(Item item);

    List<Item> getAvailable();
}

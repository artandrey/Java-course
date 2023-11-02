package com.example.services;

import java.util.ArrayList;
import java.util.List;

import com.example.exceptions.LibraryException;
import com.example.models.Patron;
import com.example.models.Items.Item;

public class Library implements IManageable {
    private List<Item> items = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();

    public void registerPatron(Patron patron) {
        patrons.add(patron);
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }

    private void assertPatronAccess(Patron patron) throws LibraryException {
        if (!patrons.contains(patron)) {
            throw new LibraryException("This patron is nor registered");
        }
    }

    public void lendItem(Patron patron, Item item) throws LibraryException {
        assertPatronAccess(patron);
        patron.borrowItem(item);
    }

    public void returnItem(Patron patron, Item item) throws LibraryException {
        assertPatronAccess(patron);
        patron.returnItem(item);
    }

    @Override
    public List<Item> getAvailable() {
        return items.stream().filter(item -> !item.isBorrowed()).toList();
    }

}

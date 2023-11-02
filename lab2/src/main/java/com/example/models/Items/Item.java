package com.example.models.Items;

import com.example.models.BaseModel;
import com.example.models.Items.interfaces.IItem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Item extends BaseModel implements IItem {
    private String title;
    private boolean isBorrowed = false;

    public Item(String title) {
        this.title = title;

    }

    public Item(String title, long id, boolean isBorrowed) {
        this.title = title;
        this.isBorrowed = isBorrowed;
    }

    public void borrowItem() {
        this.isBorrowed = true;
    }

    public void returnItem() {
        this.isBorrowed = false;
    }

}

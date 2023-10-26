package com.example.models;

import com.example.models.interfaces.IProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends ModelBase implements IProduct {

    private final double price;

    private final String title;

    public Product(String title, double price) {
        this.price = price;
        this.title = title;
    }

}

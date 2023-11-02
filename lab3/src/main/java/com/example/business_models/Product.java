package com.example.business_models;

import com.example.business_models.interfaces.IProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseModel implements IProduct {

    private final double price;

    private final String title;

    public Product(String title, double price) {
        this.price = price;
        this.title = title;
    }

}

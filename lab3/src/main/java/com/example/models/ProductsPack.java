package com.example.models;

import com.example.models.interfaces.IProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductsPack extends ProductsContainer implements IProduct {

    private final String title;

    public ProductsPack(String title) {
        this.title = title;
    }

}

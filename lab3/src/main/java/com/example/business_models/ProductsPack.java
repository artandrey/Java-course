package com.example.business_models;

import com.example.business_models.interfaces.IProduct;

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

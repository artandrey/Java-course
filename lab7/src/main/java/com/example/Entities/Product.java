package com.example.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity implements Comparable<Product> {
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        super(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public int compareTo(Product comparedProduct) {
        if (comparedProduct.getPrice() == this.getPrice()) {
            return 0;
        }
        return comparedProduct.getPrice() > this.getPrice() ? 1 : -1;
    }

}

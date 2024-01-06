package com.example.entities;

import java.util.Comparator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString
public class Product extends BaseEntity implements Comparable<Product> {
    private @NonNull String name;
    private @NonNull double price;
    @Setter
    private @NonNull int stock;

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

    public static Comparator<Product> NameComparator = (productA, productB) -> productA.getName().compareTo(productB.getName());

    public static Comparator<Product> StockComparator = (productA, productB) -> productA.getName().compareTo(productB.getName());

}

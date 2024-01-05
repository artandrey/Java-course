package com.example.Entities;

import java.util.Map;
import java.util.Optional;

import lombok.Getter;

public class User extends BaseEntity {
    @Getter
    private String username;
    private Map<Product, Integer> cart;

    public User(int id, String username) {
        super(id);
        this.username = username;
    }

    private int getProductCount(Product product) {
        return Optional.of(cart.get(product)).orElse(0);
    }

    private void setProductQuantity(Product product, int quantity) {
        var productQuantity = getProductCount(product) + quantity;
        if (productQuantity > 0) {
            cart.put(product, productQuantity);
        } else {
            cart.remove(product);
        }
    }

    public void addProducts(Product product) {
        addProducts(product, 1);
    }

    public void addProducts(Product product, int quantity) {
        setProductQuantity(product, quantity);
    }

    public void removeProducts(Product product) {
        removeProducts(product, 1);
    }

    public void removeProducts(Product product, int quantity) {
        setProductQuantity(product, -quantity);
    }

}

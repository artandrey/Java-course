package com.example.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User extends BaseEntity {
    @Getter
    private @NonNull String username;
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
        cart.remove(product);
    }

    public void removeProducts(Product product, int quantity) {
        setProductQuantity(product, -quantity);
    }

    public Map<Product, Integer> getCart() {
        return this.cart.entrySet().stream().collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

}

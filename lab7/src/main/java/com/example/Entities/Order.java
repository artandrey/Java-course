package com.example.entities;

import java.util.Map;

import lombok.Getter;

@Getter
public class Order extends BaseEntity {
    private final int userId;
    private final Map<Product, Integer> orderDetails;
    private final double totalPrice;

    public Order(int id, int userId, Map<Product, Integer> orderDetails) {
        super(id);
        this.userId = userId;
        this.orderDetails = orderDetails;
        this.totalPrice = getTotalPrice(orderDetails);
    }

    public Order(int userId, Map<Product, Integer> orderDetails) {
        this.userId = userId;
        this.orderDetails = orderDetails;
        this.totalPrice = getTotalPrice(orderDetails);
    }

    private double getTotalPrice(Map<Product, Integer> orderDetails) {
        return orderDetails.keySet().stream().mapToDouble(product -> product.getPrice()).sum();
    }

}

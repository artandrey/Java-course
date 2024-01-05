package com.example.Entities;

import java.util.Map;

import lombok.Getter;

@Getter
public class Order extends BaseEntity {
    private int userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order(int id, int userId, Map<Product, Integer> orderDetails, double totalPrice) {
        super(id);
        this.userId = userId;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
    }

}

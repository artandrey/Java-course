package com.example;

import java.util.HashMap;
import java.util.Map;

import com.example.Entities.Order;
import com.example.Entities.Product;
import com.example.Entities.User;

public class ECommercePlatform {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Order> orders = new HashMap<>();
}

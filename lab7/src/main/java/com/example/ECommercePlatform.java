package com.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.entities.Order;
import com.example.entities.Product;
import com.example.entities.User;
import com.example.exceptions.EntityNotFoundException;

public class ECommercePlatform {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Order> orders = new HashMap<>();

    public void createUser(String username) {
        User user = new User(username);
        users.put(user.getId(), user);
    }

    public void addProducts(String name, double price, int stock) {
        Product product = new Product(name, price, stock);
        products.put(product.getId(), product);
    }

    private User getUserById(int userId) {
        User user = Optional.of(users.get(userId)).orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        return user;
    }

    private Product getProductById(int productId) {
        Product product = Optional.of(products.get(productId)).orElseThrow(() -> new EntityNotFoundException(productId, Product.class));
        return product;
    }

    public void addProductToCart(int userId, int productId, int quantity) {
        User user = getUserById(userId);
        Product product = getProductById(productId);
        user.addProducts(product, quantity);
    }

    public void removeProductFromCart(int userId, int productId) {
        User user = getUserById(userId);
        Product product = getProductById(productId);
        user.addProducts(product);
    }

    public void removeProductFromCart(int userId, int productId, int quantity) {
        User user = getUserById(userId);
        Product product = getProductById(productId);
        user.removeProducts(product, quantity);
    }

    public void createOrder(int userId) {
        User user = getUserById(userId);
        Order order = new Order(user.getId(), user.getCart());
        orders.put(order.getId(), order);
    }

    public List<Product> getProducts() {
        return products.values().stream().toList();
    }

    public List<Product> getProducts(Comparator<Product> sortingComparator) {
        return getProducts(sortingComparator, SortDirection.ASC);
    }

    public List<Product> getProducts(Comparator<Product> sortingComparator, SortDirection sortDirection) {
        return products.values().stream().sorted(sortDirection == SortDirection.ASC ? sortingComparator : sortingComparator.reversed()).toList();
    }
}

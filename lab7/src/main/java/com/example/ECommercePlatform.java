package com.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.entities.Order;
import com.example.entities.Product;
import com.example.entities.User;
import com.example.exceptions.EntityNotFoundException;
import com.example.exceptions.InsufficientQuanitytException;
import com.example.exceptions.NegativeQuantityException;

public class ECommercePlatform {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Product> products = new HashMap<>();
    private final Map<Integer, Order> orders = new HashMap<>();

    public void createUser(String username) {
        User user = new User(username);
        users.put(user.getId(), user);
    }

    public void addProducts(String name, double price, int stock, String category) {
        Product product = new Product(name, price, stock, category);
        products.put(product.getId(), product);
    }

    public int incrementProductStockBy(int productId, int quantity) {
        if (quantity <= 0) {
            throw new NegativeQuantityException(quantity);
        }
        Product product = getProductById(productId);
        int updatedStock = product.getStock() + quantity;
        product.setStock(updatedStock);
        return updatedStock;
    }

    private User getUserById(int userId) {
        User user = Optional.ofNullable(users.get(userId)).orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        return user;
    }

    private Product getProductById(int productId) {
        Product product = Optional.ofNullable(products.get(productId)).orElseThrow(() -> new EntityNotFoundException(productId, Product.class));
        return product;
    }

    public void addProductToCart(int userId, int productId, int quantity) {
        User user = getUserById(userId);
        Product product = getProductById(productId);
        if (quantity <= 0) {
            throw new NegativeQuantityException(quantity);
        }
        if (product.getStock() < quantity) {
            throw new InsufficientQuanitytException(quantity, product.getStock());
        }
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
        if (quantity <= 0) {
            throw new NegativeQuantityException(quantity);
        }
        if (product.getStock() < quantity) {
            throw new InsufficientQuanitytException(quantity, product.getStock());
        }
        user.removeProducts(product, quantity);
    }

    public void createOrder(int userId) {
        User user = getUserById(userId);
        Order order = new Order(user.getId(), user.getCart());
        orders.put(order.getId(), order);
        user.clearCart();
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

    public List<User> getUsers() {
        return users.values().stream().toList();
    }

    public List<Order> getOrders() {
        return orders.values().stream().toList();
    }

    public List<Order> getOrdersByUser(int userId) {
        User user = getUserById(userId);
        return orders.values().stream().filter(order -> order.getUserId() == user.getId()).toList();
    }

    public List<Product> getUserRecommendedProducts(int userId, int quantity) {
        String userMajorCategory = getUserMajorProductCategory(userId);
        return getProducts().stream().filter(product -> product.getCategory().equals(userMajorCategory)).limit(quantity).toList();
    }

    private String getUserMajorProductCategory(int userId) {
        List<Order> userOrders = getOrdersByUser(userId);

        Map<String, Integer> categoryPurchasesCount = new HashMap<>();
        userOrders.stream().forEachOrdered(order -> {
            var orderDetails = order.getOrderDetails();
            orderDetails.entrySet().forEach(detailEntry -> {
                int quantity = detailEntry.getValue();
                String category = detailEntry.getKey().getCategory();
                int categoryPurchaseCount = Optional.ofNullable(categoryPurchasesCount.get(category)).orElse(0);
                categoryPurchasesCount.put(category, categoryPurchaseCount + quantity);
            });
        });

        int maxPurchasedCount = Collections.max(categoryPurchasesCount.values());

        return categoryPurchasesCount.entrySet().stream().filter(categoryPurchasesCountEntry -> categoryPurchasesCountEntry.getValue() == maxPurchasedCount).findFirst()
                .orElseThrow(() -> new RuntimeException("No major product category was found")).getKey();
    }
}

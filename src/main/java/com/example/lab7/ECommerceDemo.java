package com.example.lab7;

import java.util.List;

import com.example.lab7.Entities.Product;
import com.example.lab7.exceptions.EntityNotFoundException;
import com.example.lab7.exceptions.InsufficientQuanitytException;
import com.example.lab7.exceptions.NegativeQuantityException;

public class ECommerceDemo {
    private static ECommercePlatform eCommercePlatform = new ECommercePlatform();

    public static void main(String[] args) {

        eCommercePlatform.createUser("User1");
        eCommercePlatform.createUser("User2");

        eCommercePlatform.addProducts("Sofa", 299.99, 20, "Home Goods");
        eCommercePlatform.addProducts("Bedding Set", 79.99, 30, "Home Goods");
        eCommercePlatform.addProducts("Coffee Table", 149.99, 15, "Home Goods");
        eCommercePlatform.addProducts("Laptop", 999.99, 10, "Electronics");
        eCommercePlatform.addProducts("Smartphone", 499.99, 25, "Electronics");
        eCommercePlatform.addProducts("Headphones", 79.99, 50, "Electronics");
        eCommercePlatform.addProducts("T-Shirt", 19.99, 100, "Clothing");
        eCommercePlatform.addProducts("Jeans", 39.99, 50, "Clothing");
        eCommercePlatform.addProducts("Jacket", 89.99, 30, "Clothing");
        addProductToCart(1, 3, 3);
        addProductToCart(1, 5, 2);
        addProductToCart(1, 10, 1);
        addProductToCart(1, 11, 1);
        addProductToCart(1, 11, 200);
        eCommercePlatform.createOrder(1);

        addProductToCart(2, 4, 2);
        addProductToCart(2, 6, 1);
        addProductToCart(2, 7, 1);

        removeProductFromCart(1, 3);
        removeProductFromCart(2, 4, 5);
        removeProductFromCart(2, 3, -5);

        addProductToCart(100, 7, 1);

        System.out.println("Products:");
        eCommercePlatform.getProducts().forEach(product -> System.out.println(product.getName()));

        System.out.println("Users:");
        eCommercePlatform.getUsers().forEach(user -> System.out.println(user.getUsername()));

        System.out.println("User recommended products");
        List<Product> user1RecommendedProducts = eCommercePlatform.getUserRecommendedProducts(1, 2);
        user1RecommendedProducts.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));

        System.out.println("Sorted Products by price (ASC):");
        List<Product> sortedProductsAsc = eCommercePlatform.getProducts(Product.PriceComparator);
        sortedProductsAsc.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));

        System.out.println("Sorted Products by price (DESC):");
        List<Product> sortedProductsDesc = eCommercePlatform.getProducts(Product.PriceComparator, SortDirection.DESC);
        sortedProductsDesc.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));

        System.out.println("Sorted Products by name (ASC):");
        List<Product> sortedProductsByNameAsc = eCommercePlatform.getProducts(Product.NameComparator);
        sortedProductsByNameAsc.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));

        System.out.println("Sorted Products by name (DESC):");
        List<Product> sortedProductsByNameDesc = eCommercePlatform.getProducts(Product.NameComparator, SortDirection.DESC);
        sortedProductsByNameDesc.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));

        System.out.println("Sorted Products by stock (ASC):");
        List<Product> sortedProductsByStockAsc = eCommercePlatform.getProducts(Product.StockComparator);
        sortedProductsByStockAsc.forEach(product -> System.out.println(product.getName() + " - Stock: " + product.getStock()));

        System.out.println("Sorted Products by stock (DESC):");
        List<Product> sortedProductsByStockDesc = eCommercePlatform.getProducts(Product.StockComparator, SortDirection.DESC);
        sortedProductsByStockDesc.forEach(product -> System.out.println(product.getName() + " - Stock: " + product.getStock()));

        eCommercePlatform.createOrder(1);
        eCommercePlatform.createOrder(2);

        System.out.println("Orders:");
        eCommercePlatform.getOrders().forEach(order -> {
            System.out.println("Order ID: " + order.getId() + ", User: " + order.getUserId());
            System.out.println("Products:");
            order.getOrderDetails().forEach((product, quantity) -> System.out.println(product.getName() + " - Quantity: " + quantity));
            System.out.println("Total Price: $" + order.getTotalPrice());
            System.out.println();
        });
    }

    private static void addProductToCart(int userId, int productId, int quantity) {
        try {
            eCommercePlatform.addProductToCart(userId, productId, quantity);
        } catch (InsufficientQuanitytException | NegativeQuantityException | EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void removeProductFromCart(int userId, int productId) {
        try {
            eCommercePlatform.removeProductFromCart(userId, productId);
        } catch (InsufficientQuanitytException | NegativeQuantityException | EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void removeProductFromCart(int userId, int productId, int quantity) {
        try {
            eCommercePlatform.removeProductFromCart(userId, productId, quantity);
        } catch (InsufficientQuanitytException | NegativeQuantityException | EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

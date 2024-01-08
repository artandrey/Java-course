package com.example.lab7;

/**
 * Unit test for simple App.
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.lab7.Entities.Product;
import com.example.lab7.exceptions.EntityNotFoundException;
import com.example.lab7.exceptions.InsufficientQuanitytException;
import com.example.lab7.exceptions.NegativeQuantityException;
import com.example.lab7.util.IdGenerator;

class ECommercePlatformTest {
    private ECommercePlatform ecommercePlatform;

    @BeforeEach
    void setUp() {
        ecommercePlatform = new ECommercePlatform();
        IdGenerator.reset();
    }

    @Test
    void createUser() {
        ecommercePlatform.createUser("user1");
        assertEquals(1, ecommercePlatform.getUsers().size());
    }

    @Test
    void addProducts() {
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        assertEquals(1, ecommercePlatform.getProducts().size());
    }

    @Test
    void incrementProductStockBy() {
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        int productId = ecommercePlatform.getProducts().get(0).getId();
        int updatedStock = ecommercePlatform.incrementProductStockBy(productId, 5);
        assertEquals(15, updatedStock);
    }

    @Test
    void addProductToCart() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        ecommercePlatform.addProductToCart(1, 2, 3);

        assertEquals(1, ecommercePlatform.getUsers().get(0).getCart().size());
    }

    @Test
    void removeProductFromCart() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        ecommercePlatform.addProductToCart(1, 2, 3);
        ecommercePlatform.removeProductFromCart(1, 2);

        assertEquals(0, ecommercePlatform.getUsers().get(0).getCart().size());
    }

    @Test
    void createOrder() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        ecommercePlatform.addProductToCart(1, 2, 3);
        ecommercePlatform.createOrder(1);

        assertEquals(1, ecommercePlatform.getOrders().size());
        assertEquals(0, ecommercePlatform.getUsers().get(0).getCart().size());
    }

    @Test
    void getProductsSorted() {
        ecommercePlatform.addProducts("Product2", 30.0, 5, "Category2");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");

        List<Product> sortedProducts = ecommercePlatform.getProducts(Comparator.comparing(Product::getPrice));
        assertEquals("Product2", sortedProducts.get(0).getName());
        assertEquals("Product1", sortedProducts.get(1).getName());
    }

    @Test
    void getUserRecommendedProducts() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        ecommercePlatform.addProducts("Product2", 30.0, 5, "Category2");
        ecommercePlatform.addProductToCart(1, 2, 3);
        ecommercePlatform.createOrder(1);

        List<Product> recommendedProducts = ecommercePlatform.getUserRecommendedProducts(1, 1);
        assertEquals(1, recommendedProducts.size());
        assertEquals("Product1", recommendedProducts.get(0).getName());
    }

    @Test
    void addProductToCart_ThrowNegativeQuantityException() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");

        assertThrows(NegativeQuantityException.class, () -> ecommercePlatform.addProductToCart(1, 2, -3));
    }

    @Test
    void addProductToCart_ThrowInsufficientQuantityException() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 5, "Category1");

        assertThrows(InsufficientQuanitytException.class, () -> ecommercePlatform.addProductToCart(1, 2, 10));
    }

    @Test
    void removeProductFromCart_ThrowNegativeQuantityException() {
        ecommercePlatform.createUser("user1");
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");
        ecommercePlatform.addProductToCart(1, 2, 3);

        assertThrows(NegativeQuantityException.class, () -> ecommercePlatform.removeProductFromCart(1, 2, -2));
    }

    @Test
    void incrementProductStockBy_ThrowNegativeQuantityException() {
        ecommercePlatform.addProducts("Product1", 50.0, 10, "Category1");

        assertThrows(NegativeQuantityException.class, () -> ecommercePlatform.incrementProductStockBy(1, -3));
    }

    @Test
    void getUserById_ThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> ecommercePlatform.getOrdersByUser(1));
    }

    @Test
    void getProductById_ThrowEntityNotFoundException() {
        ecommercePlatform.createUser("user1");
        assertThrows(EntityNotFoundException.class, () -> ecommercePlatform.addProductToCart(1, 2, 10));
    }

}

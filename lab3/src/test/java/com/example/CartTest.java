package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.business_models.Customer;
import com.example.business_models.interfaces.ICart;
import com.example.business_models.interfaces.IProduct;
import com.example.business_models.order.Order;
import com.example.util.IdGenerator;

public class CartTest {
    private ICart cart;

    @BeforeEach
    private void setup() {
        cart = mock(ICart.class);
    }

    @Test
    public void testAddProduct() {
        IProduct productToAdd = mock(IProduct.class);
        when(productToAdd.getId()).thenReturn(IdGenerator.generateUUID());
        when(productToAdd.getTitle()).thenReturn("Water");
        when(productToAdd.getPrice()).thenReturn(100.56);

        when(cart.getPrice()).thenReturn(100.56);

        cart.addProduct(productToAdd);

        assertEquals(cart.getPrice(), 100.56);
    }

    @Test
    public void testOrderCreation() {
        when(cart.getPrice()).thenReturn(100.56);
        Customer customer = mock(Customer.class);

        Order order = new Order.OrderBuilder(customer, cart).build();

        assertEquals(order.getCart().getPrice(), 100.56);

    }

}

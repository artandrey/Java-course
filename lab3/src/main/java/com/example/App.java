package com.example;

import com.example.models.Cart;
import com.example.models.Customer;
import com.example.models.Product;
import com.example.models.ProductsPack;
import com.example.models.order.Order;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Product bread = new Product("Bread", 2.99);
        Product water = new Product("Water", 0.99);
        Product jar = new Product("Jar", 5.99);

        ProductsPack bottlesPack = new ProductsPack("Bottles Pack");

        bottlesPack.addProduct(water).addProduct(water).addProduct(water);

        ProductsPack jarsPack = new ProductsPack("Jars Pack");

        jarsPack.addProduct(jar).addProduct(jar);

        ProductsPack campingPack = new ProductsPack("Camping pack");

        campingPack.addProduct(bottlesPack).addProduct(bread);

        Cart cart = new Cart();

        cart.addProduct(campingPack).addProduct(water).addProduct(jarsPack);

        Customer client1 = new Customer();

        Customer client2 = new Customer();

        Order order = new Order.OrderBuilder(client1, cart).setReceiver(client2).build();

        System.out.println(order.getCart());

    }
}

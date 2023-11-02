package com.example;

import com.example.business_models.Cart;
import com.example.business_models.Customer;
import com.example.business_models.Product;
import com.example.business_models.ProductsPack;
import com.example.business_models.order.Order;

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

        Customer client1 = new Customer("Andrii", "+3800000000000");

        Customer client2 = new Customer("Andrii 2", "+380000000001");

        Order order = new Order.OrderBuilder(client1, cart).setReceiver(client2).build();

        System.out.println(order.getCart());

    }
}

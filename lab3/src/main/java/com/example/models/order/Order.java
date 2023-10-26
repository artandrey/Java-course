package com.example.models.order;

import java.util.Optional;

import com.example.models.Cart;
import com.example.models.Customer;
import com.example.models.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends ModelBase {

    private OrderStatus status = OrderStatus.PENDING;

    private final Customer client;

    private final Cart cart;

    private final Optional<Customer> receiver;

    private final boolean call;

    private Order(Customer client, Optional<Customer> receiver, boolean call, Cart cart) {
        this.client = client;
        this.receiver = receiver;
        this.call = call;
        this.cart = cart;
    }

    public Customer getReceiver() {
        return receiver.orElse(client);
    }

    @Data
    public final static class OrderBuilder {

        private final Customer client;

        private Optional<Customer> receiver;

        private boolean call = false;

        private final Cart cart;

        public OrderBuilder(Customer client, Cart cart) {
            this.client = client;
            this.cart = cart;
        }

        public OrderBuilder setReceiver(Customer receiver) {
            this.receiver = Optional.of(receiver);
            return this;
        }

        public OrderBuilder setCall(boolean call) {
            this.call = call;
            return this;
        }

        public Order build() {
            return new Order(client, receiver, call, cart);
        }

    }

}

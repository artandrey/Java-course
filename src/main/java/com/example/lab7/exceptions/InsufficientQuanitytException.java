package com.example.lab7.exceptions;

import lombok.Getter;

@Getter
public class InsufficientQuanitytException extends RuntimeException {
    private int quantity;
    private int stock;

    public InsufficientQuanitytException(int quantity, int stock) {
        super("Provided quantity: " + quantity + " is greater than product stock: " + stock);
        this.quantity = quantity;
        this.stock = stock;
    }

}

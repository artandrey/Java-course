package com.example.lab7.exceptions;

public class NegativeQuantityException extends RuntimeException {
    public NegativeQuantityException(int quantity) {
        super("Incorect quantity: Provided negative value: " + quantity);
    }
}

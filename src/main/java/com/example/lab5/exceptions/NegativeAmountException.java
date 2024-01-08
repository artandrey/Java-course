package com.example.lab5.exceptions;


import lombok.Getter;

@Getter
public class NegativeAmountException extends IllegalArgumentException {
    private final double amount;

    public NegativeAmountException(double amount) {
        super("Amount should be more than 0. Provided amount: " + amount);
        this.amount = amount;
    }
}

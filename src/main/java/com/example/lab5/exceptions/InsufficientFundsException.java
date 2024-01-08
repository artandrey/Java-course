package com.example.lab5.exceptions;

import lombok.Getter;

@Getter
public class InsufficientFundsException extends RuntimeException {
    private final double balance;
    private final double withdrawAmount;

    public InsufficientFundsException(double balance, double withdrawAmount) {
        super("Cannot withdraw " + withdrawAmount + " from account. Insufficient funds. Current account`s balance: " + balance);
        this.balance = balance;
        this.withdrawAmount = withdrawAmount;
    }
}

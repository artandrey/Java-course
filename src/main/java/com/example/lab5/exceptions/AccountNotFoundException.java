package com.example.lab5.exceptions;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {
    private final long accountNumber;

    public AccountNotFoundException(long accountNumber) {
        super("Account not found for account number: " + accountNumber);
        this.accountNumber = accountNumber;
    }
}

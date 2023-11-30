package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BankAccount {
    @Getter
    private long accountNumber;
    @Getter
    private String accountName;
    @Getter
    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void getAccountSummary() {

    }
}

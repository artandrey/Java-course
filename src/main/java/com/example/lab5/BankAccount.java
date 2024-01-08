package com.example.lab5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
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
        System.out.println(this.toString());
    }
}

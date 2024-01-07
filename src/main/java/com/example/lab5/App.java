package com.example.lab5;

import com.example.lab5.Exceptions.AccountNotFoundException;
import com.example.lab5.Exceptions.InsufficientFundsException;
import com.example.lab5.Exceptions.NegativeAmountException;

/**
 * Hello world!
 *
 */
public class App {

    private static Bank bank = new Bank();

    public static void main(String[] args) {

        long bossAccountNumber = registerAccount("Kyryl", 1000);
        long andriiAccountNumber = registerAccount("Andrii", 1000.0);
        long dimaAccountNumber = registerAccount("Dima", 500.0);
        long badAccountNumber = registerAccount("Bad account", -100.0);
        System.out.println(badAccountNumber);

        showAccountInfo(0);
        showAccountInfo(andriiAccountNumber);
        showAccountInfo(dimaAccountNumber);

        doTransatction(bossAccountNumber, andriiAccountNumber, 500);
        doTransatction(bossAccountNumber, dimaAccountNumber, 600);

    }

    private static long registerAccount(String string, double initialDesposit) {
        try {
            return bank.createAccount(string, initialDesposit);
        } catch (NegativeAmountException exception) {
            System.out.println("Bank registration error occured");
            System.out.println(exception.getMessage());
            return -1;
        }
    }

    private static void showAccountInfo(long accountNumber) {
        try {
            BankAccount account = bank.findAccount(accountNumber);
            account.getAccountSummary();
        } catch (AccountNotFoundException exception) {
            System.out.println("Bank search error occured");
            System.out.println(exception.getMessage());
        }
    }

    private static void doTransatction(long fromAccountNumber, long toAccountNumber, double amount) {
        try {
            bank.transferMoney(toAccountNumber, toAccountNumber, amount);
        } catch (InsufficientFundsException exception) {
            System.out.println("Bank transaction error occured");
            System.out.println(exception.getMessage());
        } catch (NegativeAmountException exception) {
            System.out.println("Bank transaction error occured");
            System.out.println(exception.getMessage());
        } catch (AccountNotFoundException exception) {
            System.out.println("Bank transaction error occured");
            System.out.println(exception.getMessage());
        }
    }
}

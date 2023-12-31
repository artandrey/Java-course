# Лабораторна робота 5

Обробка виняткових ситуацій в Java

## Мета:

Зрозуміти базові принципи обробки виняткових ситуацій в Java.
Створити спеціалізовані класи винятків для обробки конкретних помилкових сценаріїв.
Використовувати пропагацію винятків.

## Опис:

У цій лабораторній роботі вам потрібно реалізувати надійну і стійку до помилок програму, яка імітує спрощену банківську систему. Ця система буде включати створення рахунків, фінансові операції і функції зведення рахунків. Ваше завдання полягає в тому, щоб переконатися, що програма може елегантно обробляти різні типи помилок, не ламаючись. Реалізуйте спеціалізовані класи винятків для обробки спеціалізованих сценаріїв помилок.

## План розробки:

-   У ході виконання завдання, було реалізовано клас `BankAccount`, що містить у собі члени класу `accountNumber`, `accountName` та `balance`. Останні дозволяють зберігати основну інформацію про банківський рахунок. Дані члени були створені з метою забезпечення можливості ідентифікації рахунку та відображення актуального балансу.

-   Для реалізації функціоналу операцій з рахунком були створені методи `deposit(double amount)`, `withdraw(double amount)`, `getBalance()` та `getAccountSummary()`. Методи `deposit` та `withdraw` використовуються для здійснення операцій зняття та поповнення коштів відповідно, забезпечуючи валідацію на від'ємні суми. Метод `getBalance` надає можливість отримати поточний баланс рахунку, а метод `getAccountSummary` - отримати комплексну інформацію про рахунок.

-   Для обробки виняткових ситуацій були створені спеціалізовані класи винятків: `InsufficientFundsException`, `NegativeAmountException` та `AccountNotFoundException`. Кожен з них використовується для відловлення конкретного виду помилки, такого як недостатні кошти на рахунку, спроба внесення від'ємної суми чи відсутність рахунку.

```java
@Getter
public class AccountNotFoundException extends RuntimeException {
    private final long accountNumber;

    public AccountNotFoundException(long accountNumber) {
        super("Account not found for account number: " + accountNumber);
        this.accountNumber = accountNumber;
    }
}
```

```java
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
```

```java
@Getter
public class NegativeAmountException extends IllegalArgumentException {
    private final double amount;

    public NegativeAmountException(double amount) {
        super("Amount should be more than 0. Provided amount: " + amount);
        this.amount = amount;
    }
}
```

-   Для управління об'єктами `BankAccount` був створений клас `Bank`, який зберігає колекцію об'єктів `BankAccount`. Метод `createAccount` використовується для створення нового банківського рахунку з вказаною назвою та початковим депозитом. Метод `findAccount` призначений для пошуку рахунку за його номером. Метод `transferMoney` використовується для здійснення переказу коштів між рахунками.

```java
public long createAccount(String accountName, double initialDeposit) throws NegativeAmountException
```

```java
public BankAccount findAccount(long accountNumber) throws AccountNotFoundException
```

```java
public void transferMoney(long fromAccountNumber, long toAccountNumber, double amount) throws AccountNotFoundException, NegativeAmountException, InsufficientFundsException
```

-   Всі вказані методи класу `Bank` обробляють винятки, що можуть виникнути в ході їх виконання. Для тестування виняткових ситуацій були створені відповідні тестові класи, які моделюють різні сценарії використання та перевіряють коректність обробки помилок.

1. Демонстрація обробки помилов у файлі [App.java](./lab5/src/main/java/com/example/App.java);
2. Тести для перевірки на вийняткові ситуації у файлі [AppTest.java](./lab5/src/test/java/com/example/AppTest.java)

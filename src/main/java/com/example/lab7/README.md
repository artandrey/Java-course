# Лабораторна Робота 7

## Функціональність

Необхідно реалізувати спрощену систему бек-енду для платформи електронної комерції, використовуючи Java Collections, зосереджуючись на управлінні запасами, управлінні корзиною користувача та обробці замовлень.

## Вимоги

### Створити класи

### Product (Товар)

-   Поля: id, name, price, stock
-   Конструктори та гетери реалізовані.

### User (Користувач)

-   Поля: id, cart
-   Конструктори та гетери реалізовані.

### Order (Замовлення)

-   Поля: id, userId, orderDetails
-   Конструктори та гетери реалізовані.

### Платформа Електронної Комерції ECommercePlatform

-   Поля: users, products, orders
-   Методи для додавання користувачів, товарів, створення замовлення, переліку доступних товарів, користувачів, замовлень та оновлення запасів товарів.

### ECommerceDemo

-   Клас для демонстрації функціональності.

## Розширені Функції

### Сортування та фільтрація

-   Реалізовано Comparable у класі Product.
-   Клас Comparator для сортування Товарів.

### Рекомендації для Користувача

-   Функція для рекомендації товарів користувачам.

## Реалізація

### Entities

-   Створено клас [`BaseEntity`](./lab7/src/main/java/com/example/entities/BaseEntity.java), що містить у собі базову логіку: роботу з id.
-   Розроблено класи, що представляють дані у базі даних: [`Product`](./lab7/src/main/java/com/example/entities/Product.java), [`User`](./lab7/src/main/java/com/example/entities/User.java), [`Order`](./lab7/src/main/java/com/example/entities/Order.java).

### ECommercePlatform

-   Створено клас [`ECommercePlatform`](./lab7/src/main/java/com/example/ECommercePlatform.java) у якому є методи для створення нових `Product`, `User`, `Order` (`createUser`, `addProducts`, `createOrder`), управління корзиною `User`
    (`addProductToCart` `removeProductFromCart`) та оновлення запасів для `Product` (`incrementProductStockBy`).
-   Також було реалізовано методи для виведення `Product`, `User`, `Order` (`getProducts`, `getUsers`, `getOrders`), для `Product` було додано можливість виведення з сортуванням:

```java
// ECommerceDemo.java
System.out.println("Sorted Products by price (DESC):");
List<Product> sortedProductsDesc = eCommercePlatform.getProducts(Product.PriceComparator, SortDirection.DESC);
sortedProductsDesc.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));
```

-   Реалізовано метод `getUserRecommendedProducts` для виведення рекомендацій для окремого користувача, що виводить товари на основі індивідуальної історії покупок користувача.

### Винятки

-   Розроблено винятки, що покривають випадки отримання неіснуючого ресурсу, введення некоректних даних або недостатньої кількості ресурсів:
    -   [`EntityNotFoundException`](./lab7/src/main/java/com/example/exceptions/EntityNotFoundException.java) (`Product`, `User`, `Order` за даним id не знайдено)
    -   [`InsufficientQuanitytException`](./lab7/src/main/java/com/example/exceptions/InsufficientQuanitytException.java) (Кількість товарів, які намагаються купити переважає запаси на складі)
    -   [`NegativeQuantityException`](./lab7/src/main/java/com/example/exceptions/NegativeQuantityException.java) (У полі кількість було введено від'ємне або нульове значення, що може спричинити некоректну роботу програми)

### Демонстрація Функціональності

-   Для демонстрації функціональності програми розроблено клас [`ECommerceDemo`](./lab7/src/main/java/com/example/ECommerceDemo.java).

### Модульні Тести

-   Створено тести у файлі [`EcommercePlatformTest`](./lab7/src/test/java/com/example/ECommercePlatformTest.java), що покривають весь функціонал, включаючи роботу з вийнятковими ситуаціями.

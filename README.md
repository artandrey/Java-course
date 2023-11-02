# Лабораторна робота 3

## Завдання

1. Створіть базову систему електронної комерції, яка складається з сутностей Product, Cart та Order. Система повинна дозволяти користувачам:

-   Додавати продукти до кошика.
-   Видаляти продукти з кошика.
-   Робити замовлення з товарів у кошику.
-   Перевіряти статус замовлення.

2. Після реалізації системи електронної комерції:

-   Напишіть тестові випадки JUnit для тестування всіх функцій.
-   Замокайте поведінку методів

### Структура проекту

Цей проект базується на бізнес-моделях і не використовує бізнес-моделі для представлення структури даних в базі даних. Бізнес-моделі використовуються для опису бізнес-логіки, об'єктів або сутностей, а не для зберігання або маніпулювання даними в базі даних.

Клас `IdGenerator` є частиною проекту і надає функціональність для генерації унікальних ідентифікаторів у формі `UUID`.

Базова логіка моделі описана у класі `ModelBase`
А саме, створення унікального ідентифікатора та імплементація інтерфейсу `IIdentifiable`, що описує методи для отримання Id сутності.

```java
@Data
public abstract class ModelBase implements IIdentifiable {

    private UUID id;

    public ModelBase() {
        this.id = IdGenerator.generateUUID();
    }

}
```

Ось приклад моделі `Customer` створенної на основі базової моделі

```java

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseModel {
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

}
```

Все максимально просто

Але важливу роль у проекті відіграють роль інтерфейси. Вони несуть у собі роль об'єкта, що дає можлвиість конкретизувати призначення окремих сутностей.

Отже,
`IValuable` - все у чого є ціна
`IProduct` - розширяє `IValuable` та `IIdentifiable`. Це те, що може бути куплено.
`IProductContainable` - це те, що може зберігати у собі продукти.

#### Патерни проектування

З метою покращити development experience та зробити основні класи відкритими для розширення та закритими для редагування було використано наступні патерни проектування.
У цьому розділі буде описано патерни, що були використані у програмі та мета їх використання.

Інтерфейси описані вище відіграють важливу роль у реалізації системи пакування у продуктів.
Основна суть полягає в тому, що ми можемо купити не тільки одиницю продукту, а ціле пакування. Наприклад, пляшка води може бути у пакуванні з 4, а ці пакування у свою чергу можуть бути зібрані у контейнері, що вміщає 8 пакувань.
Така ієрархія може бути корисною для масштабованості проекту.

##### Компонувальник (Дерево, Composite)

![Компонувальник](https://refactoring.guru/images/patterns/content/composite/composite.png)
Детальніше з ним можете ознайомитися тут
[refactoring.guru](https://refactoring.guru/uk/design-patterns/composite)

Створено клас ProductsContainer.
Цей клас може слугувати базою для бізнес моделей, що реалізовують пакування.
Та містить у собі логіку додавання продуктів до пакування, а такок логіку підрахунку ціни пакунку в залежності від вартості продуктів всередині.

```java
public abstract class ProductsContainer extends BaseModel implements IValuable, IProductContainable {
    // implementation
}
```

##### Будівельник (Builder)

![Компонувальник](https://refactoring.guru/images/patterns/content/builder/builder-uk.png)
Детальніше з ним можете ознайомитися тут
[refactoring.guru](https://refactoring.guru/uk/design-patterns/builder)

`Order` (замовлення) складається з інформації про корзину, має статус та опціональні налаштування, такі як отримувач та опція чи потрібно дзвонити користувачеві.
Замовлення може мати велику кількість параметрів, які є необов'язковими, або ж замовлення може конфігуруватися на різних стадіях, отже було прийнято рішення реалізувати цей паттерн.

Маємо ось такий зручний та лаконічний код

```java
Cart cart = new Cart();
Customer client = new Customer();
Order.OrderBuilder(client, cart);
```

замість

```java
Cart cart = new Cart();
Customer client = new Customer();
Order.OrderBuilder(client, cart, client, false);
```

Обов'язкові параметри передаються у конструктор, а опціональні за допомогою методів.
Наприклад:

```java
Cart cart = new Cart();

Customer client1 = new Customer("Andrii", "+3800000000000");
Customer client2 = new Customer("Andrii 2", "+380000000001");
Order order = new Order.OrderBuilder(client1, cart).setReceiver(client2).build();
```

#### Роль інтерфейсів

ProductsPack - це те, що можна купити, отже імплеменуємо інтерфейс IProduct, але немає необхідності писати реалізацію, бо вона вже існує в ProductsContainer, а саме: отримувати ціну.

```java
public class ProductsPack extends ProductsContainer implements IProduct {
    // implementation
}
```

Cart - це сутність, яка не планує пакування в інший пакунок. Користувач набирає продукти в корзину як в пакунок, але не може покласти корзину в іншу упаковку.
Але це теж `ProductsContainer`

```java
public class Cart extends ProductsContainer {

}
```

Таку поведінку було реалізовано за допомогою інтерфейсу `IProduct`.
Завдяки Runtime типам у Java ми отримуємо міцну систему з сильною type safety.

### Що використовувалось?

Під час роботи над проектом було використано такі інструменти як:

#### Lombok

Бібліотека, що включає у себе анотації для зменшення розміру коду, що покращує його читабельність
У даному проекті було використано такі анотації:

-   `Data` - для автоматичного створення геттерів та сеттерів для моделей
-   `EqualsAndHashCode` - використовувалась для створення методів `equals` та `hashCode` для моделей
-   `ToString` - використовувалась з метою додати вивід властивостей моделі при виведенні до консолі

### Створення тестів

// TBD

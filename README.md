# Лабораторна робота 2

## Завдання

1. Удосконаліть систему, яка дозволяє бібліотекареві керувати предметами (книгами, DVD) та клієнтами.
2. Покрити тестами функціональність програми.

## Функціональність програми

1. Додавати предмети (книги, DVD) до бібліотеки.
2. Видаляти предмети з бібліотеки.
3. Реєструвати читача.
4. Видавати предмет читачеві.
5. Повертати предмет у бібліотеку.
6. Показувати список доступних предметів.
7. Показувати список взятих предметів та їхніх читачів

## План розробки

Було створено наступні класи

### Item (Abstract Class)

#### Attributes:

-   title: String
-   uniqueID: String (unique for each item)
-   isBorrowed: boolean (default false)

#### Methods:

-   Constructors, getters, setters
-   `abstract void borrowItem()`: Makes the item as borrowed.
-   `abstract void returnItem()`: Marks the item as not borrowed.

### Book (implements Item)

#### Attributes:

-   author: String

#### Methods:

-   `borrowItem()`: Implements the abstract method from Item.
-   `returnItem()`: Implements the abstract method from Item.

### DVD (implements Item)

#### Attributes:

-   duration: int (minutes)

#### Methods:

-   `borrowItem()`: Implements the abstract method from Item.
-   `returnItem()`: Implements the abstract method from Item.

### Patron

#### Attributes:

-   name: String
-   ID: String (unique for each patron)
-   borrowedItems: List<Item>

#### Methods:

-   Constructors, getters, setters
-   `borrow(Item)`: Adds an item to the patron's borrowed list.
-   `return(Item)`: Removes an item from the patron's borrowed list.

### IManageable (Interface)

#### Methods:

-   `add(Item)`: Adds an item.
-   `remove(Item)`: Removes an item.
-   `listAvailable()`: Lists all available items.
-   `listBorrowed()`: Lists all borrowed items.

### Library (implements IManageable)

#### Attributes:

-   items: List<Item> (to store all items)
-   patrons: List<Patron> (to store all registered patrons)

#### Methods:

-   `registerPatron(Patron)`: Registers a new patron.
-   `lendItem(Patron, Item)`: Lends an item to a patron.
-   `returnItem(Patron, Item)`: Returns a borrowed item.

### Структура проекту

Класи в цьому проекті пов'язані між собою наступним чином:

-   **Item (Abstract Class)** виступає як базовий абстрактний клас для предметів у бібліотеці, який містить загальні атрибути та методи для всіх предметів.

-   **Book (implements Item)** та **DVD (implements Item)** є конкретними класами, які реалізують інтерфейс Item. Вони успадковують атрибути та методи з базового класу Item та додають власні атрибути та реалізацію абстрактних методів.

-   **Patron** представляє читача бібліотеки та має атрибути, які дозволяють відстежувати інформацію про читача та список позичених предметів.

-   **IManageable (Interface)** визначає структуру методів, які повинні бути реалізовані в класі Library для управління бібліотекою.

-   **Library (implements IManageable)** є центральним класом, який об'єднує всі інші класи. Він містить списки предметів та читачів. Крім того, він реалізує методи для додавання та видалення предметів, реєстрації читачів, видачі та повернення предметів читачам, а також відображення списків доступних і позичених предметів.

### Що використовувалось?

Під час роботи над проектом було використано такі інструменти як:

#### Lombok

Бібліотека, що включає у себе анотації для зменшення розміру коду, що покращує його читабельність
У даному проекті було використано такі анотації:

-   `Data` - для автоматичного створення геттерів та сеттерів для моделей
-   `EqualsAndHashCode` - використовувалась для створення методів `equals` та `hashCode` для моделей
-   `ToString` - використовувалась з метою додати вивід властивостей моделі при виведенні до консолі

### Створення тестів

В даному розділі представлені результати тестування програмного забезпечення за допомогою набору тестів для декількох класів.

#### Тести класу `BookTest`

У класі `BookTest` тестується функціональність класу `Book`, який представляє собою книгу.

1. **Тест `testBorrowItem`**: Цей тест перевіряє можливість взяття книги в бібліотеці та зміну стану книги на "позичена". Тобто тестує метод `borrowItem` класу `Book`.
2. **Тест `testReturnItem`**: Цей тест перевіряє можливість повернення книги в бібліотеку та зміну стану книги на "не позичена". Тобто тестує метод `returnItem` класу `Book`.

#### Тести класу `DVDTest`

У класі `DVDTest` тестується функціональність класу `DVD`, який представляє собою DVD-диск.

1. **Тест `testBorrowItem`**: Цей тест перевіряє можливість взяття DVD-диску в бібліотеці та зміну стану DVD-диску на "позичений". Тобто тестує метод `borrowItem` класу `DVD`.
2. **Тест `testReturnItem`**: Цей тест перевіряє можливість повернення DVD-диску в бібліотеку та зміну стану DVD-диску на "не позичений". Тобто тестує метод `returnItem` класу `DVD`.

#### Тести класу `LibraryTest`

У класі `LibraryTest` тестується функціональність класу `Library`, який представляє бібліотеку та взаємодію з патронами та предметами.

1. **Тест `testRegisterPatron`**: Цей тест перевіряє можливість зареєструвати патрона в бібліотеці. Тобто тестує метод `registerPatron` класу `Library`.
2. **Тест `testLendItem`**: Цей тест перевіряє можливість позичення предмету патрону та відображення правильного стану позичення предмету та патрона. Тобто тестує метод `lendItem` класу `Library`.
3. **Тест `testReturnItem`**: Цей тест перевіряє можливість повернення позиченого предмету в бібліотеку та відображення правильного стану предмету та патрона. Тобто тестує метод `returnItem` класу `Library`.
4. **Тест `testGetAvailable`**: Цей тест перевіряє, чи метод `getAvailable` класу `Library` правильно повертає список доступних предметів в бібліотеці.

#### Висновок

Тести успішно пройшли, і функціональність вказаних класів працює правильно згідно зі специфікацією. Тести допомагають переконатися в правильному функціонуванні програми та виявити можливі помилки та проблеми.

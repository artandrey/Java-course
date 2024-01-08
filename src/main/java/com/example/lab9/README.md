# Лабораторна робота 9

## Функціональність

Програма для збереження даних магазину у вигляді файлу Excel.

## Вимоги

-   Використовуючи зазначене API ([https://fakestoreapi.com/](https://fakestoreapi.com/)), отримати дані зі всіх енпоінтів.
-   Збережіть отримані дані у форматі Excel.
-   Покривайте код тестами.

## Реалізація

### Представлення даних

Розроблено класи [`CategoryDTO`](./dto/CategoryDTO.java) [`ProductDTO`](./dto/ProductDTO.java) [`UserDTO`](./dto/UserDTO.java) які представляють собою формат даних, який ми отримуємо.

### Заванатження

Клас [FakeStoreApiService](./service/FakeStoreApiService.java) має методи `getUsers` `getCategories` та `getProducts` для отримання необхідної інформації з магазину у форматі `json`.
Класи `CategoryMapper` `ProductMapper` та `UserMapper` виконують функцію парсингу `json` та перетворення його у потрібний формат.

### Збереження у формат файлу Excel

Збереження у формат Excel відбувається за допомогою бібліотеки `apache.poi`.
Для збереження файлів було створено клас [`ExcelWriter`](./excel/ExcelWriter.java).
Представлення даних у таблиці задається за допомогою наступних класів:

-   `TableFileDefinition` слугує для задання інформації про весь файл. Містить у собі дані та конфігурацію листів Excel на яких ці дані будуть відображатися.
-   `SheetDefinition` містить інформацію про колонки таблиці та спосіб отримання цих даних
-   `ColumnDefinition` характеризує окрему колонку таблиці. Містить інформацію про заголовок та дані, що мають зберігатися у цій колонці.
    За допомогою використання патерна Builder можна зручно конфігурувати листи та колонки таблиці.

### Тестування

Реалізовано різноманітні тест-кейси у файлах: [CategoryMapperTest](../../../../../test/java/com/example/lab9/CategoryMapperTest.java)
[ProductMapperTest](../../../../../test/java/com/example/lab9/ProductMapperTest.java)
[UserMapperTest](../../../../../test/java/com/example/lab9/UserMapperTest.java)
, що перевіряють коректність парсингу `json`.

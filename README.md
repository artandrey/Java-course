# Лабораторна Робота 6

## Функціональність

У цій лабораторній роботі студентам необхідно створити програму на Java, яка використовує багатовимірні масиви, маніпуляції з масивами та складні алгоритми для вирішення реальної проблеми, сприяючи глибшому розумінню масивів в Java.

## Вимоги

-   Бронювання Місць: Напишіть метод bookSeats(int hallNumber, int row, int[] seats), який приймає номер залу, номер ряду, номери місць для бронювання. Метод повинен позначити заброньовані місця, змінивши відповідні нулі на одиниці. Якщо місця заброньовані, система повинна сповістити про це користувача.

-   Скасування Бронювання: Напишіть метод cancelBooking(int hallNumber, int row, int[] seats), який скасовує бронювання, змінюючи відповідні одиниці назад на нулі.

-   Перевірка Наявності: Напишіть метод checkAvailability(int screen, int numSeats), який перевіряє, чи доступна задана кількість послідовних місць в будь-якому ряду зазначеного залу.

-   Друк Схеми Розміщення Місць: Напишіть метод printSeatingArrangement(int hallNumber), який друкує схему розміщення місць для даного залу, вказуючи заброньовані та доступні місця.

Бонус:

-   Реалізуйте метод findBestAvailable(int hallNumber, int numSeats), який знаходить та повертає найкращі доступні послідовні місця для даної кількості місць в зазначеному залі.
-   Реалізуйте метод autoBook(int hallNumber, int numSeats), який автоматично бронює найкращі доступні послідовні місця, знайдені методом findBestAvailable.

## Реалізація

1.  Було створено клас [`Theater`](./lab6/src/main/java/com/example/Theater.java) у якому було реалізовано методи для бронювання та скасування місць, перевірки наявності місць, друку схеми розміщення місць, а також знаходження та автоматичного бронювання найкращих доступних послідовних місць. Для зручності використано стратегію ранжування місць, яка може бути змінена в залежності від потреб.

2.  Було реалізовано алгоритм підбору кращих доступних місць, це проходить у 2 етапи:

        - Виставлення пріоритетів для кожного місця (Високий, Низький та Нульовий для вже зайнятих місць)
        - Після чого підбираються найкращі місця за пріоритетами. Алгоритм плаваючого вікна обере найкращий можливий варіант за сумою пріоритетів.

Код дуже добре реалізує `Open closed` принцип так як класс [`Theater`](./lab6/src/main/java/com/example/Theater.java) відкритий для розширення та закритий для модифікації за допомогою викристання патерну `Strategy`.
Було реалізовано інтерфейс

```java
public interface ISeatsRankStrategy {
    public SeatPriority rank(int rowIndex, int seatIndex, SeatStatus seatStatus, ITheater theater);
}
```

У якому описано метод `rank`, що викликається для отримання пріорітету.

Його імплементує клас `TriangleSeatRankStrategy`

```java
public class TriangleSeatRankStrategy implements ISeatsRankStrategy {
    @Override
    public SeatPriority rank(int rowIndex, int seatIndex, SeatStatus seatStatus, ITheater theater) {
        if (seatStatus == SeatStatus.BOOKED) {
            return SeatPriority.NONE;
        }
        int seatsCount = theater.getSeatsCount();
        int highestRankPlacesCount = seatsCount / 2;
        int startHighestRankIndex = Math.max(highestRankPlacesCount / 2 - rowIndex, 0);
        int endHighestRankIndex = Math.min(seatsCount - highestRankPlacesCount / 2 + rowIndex - 1, seatsCount - 1);
        if (seatIndex >= startHighestRankIndex && seatIndex <= endHighestRankIndex) {
            return SeatPriority.HIGH;
        } else {
            return SeatPriority.LOW;
        }
    }
}
```

Він описує стратегію розміщення місць за якою найкращі місця розподіляються конусоподібно, бо ніхто не хоче сидіти зкраю у передніх рядах.

```bash
Ranked Seats:
L L L L H H H H H H H H H H L L L L
L L L H H H H H H H H H H H H L L L
L L H H H H H H H H H H H H H H L L
L H H H H H H H H H H H H H H H H L
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H
H H H H H H H H H H H H H H H H H H


L - Low priority
H - High priority
```

3.  Був розроблений тестовий клас [`TheaterTest`](./lab6/src/test/java/com/example/TheaterTest.java), який включає в себе низку тестів для перевірки коректності роботи методів, таких як бронювання місць, скасування бронювання, перевірка наявності, пошук найкращих доступних місць та автоматичне бронювання.

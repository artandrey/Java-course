# Лабораторна робота 4.2

## Мета:

-   Розуміти незмінну природу рядків Java.
-   Працювати з методами рядка Java та StringBuilder.
-   Розуміти регулярні вирази та їх застосування в Java.

## Опис завдання:

Методи кодування:
У далекій галактиці існують повідомлення, зашифровані в дивних шаблонах, які потребують декодування. Вам було доручено написати програму на Java, яка розшифровує ці повідомлення.
Зашифроване повідомлення складається зі слів, розділених розділювачами, і кожне слово може бути закодовано різними методами. Ваша програма повинна виявляти метод кодування та декодувати відповідно.

-   Кодування заміни голосних: усі голосні (a, e, i, o, u) замінюються на числа: a=1, e=2, i=3, o=4, u=5. Наприклад, "t2st3ng" повинно бути "testing".
-   Кодування заміни приголосних: всі приголосні замінюються на наступний приголосний в послідовності. Наприклад, "vetviph" повинно бути "testing".

Примітка: Це рішення припускає, що методи кодування не перетинаються. У реальному світі може знадобитися додаткові методи для визначення типу кодування з більшою точністю.

## Вимоги:

-   Створіть клас Java `Decoder` зі статичними методами для кожного типу кодування.
-   Використовуйте `регулярні вирази`, щоб визначити, який метод кодування було застосовано до кожного слова.
-   Використовуйте `StringBuilder` для ефективних маніпуляцій з рядками.
-   Створіть функцію `main` для демонстрації вашого декодера з прикладами зашифрованих повідомлень.

## Порядок дій

Було створено клас `Decoder` з методами

```java
public static String decodeVovel(String input)
```

```java
public static String decodeCosonant(String input)
```

Ці 2 методи мають спільний функціонал заміни символів, різні лише умови, тому було вирішено створити private метод для заміни символів залежно від умови

```java
private static String replaceCharacters(String input, IntFunction<? extends IntStream> mapper) {
    return input.chars().flatMap(mapper).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
}
```

`mapper` - це параметр у який приходить лямбда вираз, що повертає символ, який було обрано в результаті обробки

### Заміна голосних

Для заміни голосних було створено `Map`

```java
private static final Map<Character, Character> vovelReplacePairs = new HashMap<>();

static {
    vovelReplacePairs.put('1', 'a');
    vovelReplacePairs.put('2', 'e');
    vovelReplacePairs.put('3', 'i');
    vovelReplacePairs.put('4', 'o');
    vovelReplacePairs.put('5', 'u');
}

```

Він використовується як показано нижче:

```java
public static String decodeVovel(String input) {
    return replaceCharacters(input, ch -> vovelReplacePairs.containsKey((char) ch) ? IntStream.of(vovelReplacePairs.get((char) ch)) : IntStream.of(ch));
}
```

Алгоритм простий:

1. Шукаємо відповідний ключ, що відповідає символу
2. Якщо ключ є, то повертаємо відповідне значення, якщо ні, то повертаємо символ без змін

### Заміна приголосних на наступний приголосний

Спочатку необхідно створити механізм для пошуку попереднього приголосного.
Для цього було створено окремий клас. Так буде зручніше його тестувати.

```java
public class ConsonantsUtil {
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

    public static char getPreviousConsonant(char input) {
        int index = CONSONANTS.indexOf(input);

        if (index == 0) {
            return CONSONANTS.charAt(CONSONANTS.length() - 1);
        }

        return CONSONANTS.charAt(index - 1);
    }

}

```

У класі `Decoder` було створено новий метод `isVovel` для перевірки на те, чи є символ голосним.
Перевірка на голосні проходить швидше, ніж перевірка на приголосні, тому було обрано саме цей варіант.

```java
private static boolean isVovel(char ch) {
        return "aeuoi".indexOf(ch) != -1;
    }

```

Сам метод для декодування виглядає так:

```java
public static String decodeConsonant(String input) {

        return replaceCharacters(input, ch -> IntStream.of(isVovel((char) ch) ? ch : CosonantsUtil.getPreviousConsonant((char) ch)));
    }
```

Алгоритм простий:

1. Перевіряємо чи символ позначає голосний.
2. Якщо так, то повертаємо символ без змін. Якщо ні, то замінюємо на попередній приголосний.

Далі необхідно створити regex для перевірки кодування.
Слова, що використовують кодування за заміною голосними не містять у собі голосних та обов'язково включають цифри:

```
(^([^aeiou\s]+[1-9]*)+$)
```

Слова, що закодовані перестановкою приголосних містять у собі голосні та як додаткова перевірка, не містять цифри:

```
(^[a-z]+$)
```

Заключаючим етапом стало написання методу `decode` що викликає метод `decodeWord` на кожнмоу слові та збирає результат.
Метод `decodeWord`, у свою чергу, викликає допоміжні методи `isVovelEncoded` та `isConsonantEncoded` для визначення типу кодування, післся чого використовує відповідний метод для декодування.

```java

public static String decode(String input) {
    String[] words = input.split("\\s");
    return Arrays.stream(words).map(Decoder::decodeWord).collect(Collectors.joining(" "));
}

private static String decodeWord(String word) {
    if (isVovelEncoded(word)) {
        return decodeVovel(word);
    } else if (isConsonantEncoded(word)) {
        return decodeConsonant(word);
    }
    return word;
}

private static boolean isVovelEncoded(String word) {
    return word.matches("(^([^aeiou\\s]+[1-9]*)+$)");
}

private static boolean isConsonantEncoded(String word) {
    return word.matches("(^[a-z]+$)");
}

```

У тестах було покрито можливі варіанти використання класу `Decoder`:

```java
@Test
public void shouldDecodePhrase() {
    String input = "t2st3ng vetviph boo";
    assertEquals(Decoder.decode(input), "testing testing zoo");
}

@Test
public void shouldDecodeVovels() {
    assertEquals(Decoder.decodeVovel("t2st3ng"), "testing");
}

@Test
public void shouldDecodeConsonants() {
    assertEquals(Decoder.decodeConsonant("vetviph"), "testing");
}

```

А також було додатково протестовано `ConsonantsUtil`:

```java
@Test
public void testLastLetter() {
    assertEquals(ConsonantsUtil.getPreviousConsonant('z'), 'y');
}

@Test
public void testFirstLetter() {
    assertEquals(ConsonantsUtil.getPreviousConsonant('b'), 'z');
}

@Test
public void testMiddleLetter() {
    assertEquals(ConsonantsUtil.getPreviousConsonant('v'), 't');
}
```

package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Decoder {

    private static final Map<Character, Character> vovelReplacePairs = new HashMap<>();

    static {
        vovelReplacePairs.put('1', 'a');
        vovelReplacePairs.put('2', 'e');
        vovelReplacePairs.put('3', 'i');
        vovelReplacePairs.put('4', 'o');
        vovelReplacePairs.put('5', 'u');
    }

    public static String decodeVovel(String input) {
        return replaceCharacters(input, ch -> vovelReplacePairs.containsKey((char) ch) ? IntStream.of(vovelReplacePairs.get((char) ch)) : IntStream.of(ch));
    }

    public static String decodeConsonant(String input) {

        return replaceCharacters(input, ch -> IntStream.of(isVovel((char) ch) ? ch : ConsonantsUtil.getPreviousConsonant((char) ch)));
    }

    private static boolean isVovel(char ch) {
        return "aeuoi".indexOf(ch) != -1;
    }

    private static String replaceCharacters(String input, IntFunction<? extends IntStream> mapper) {
        return input.chars().flatMap(mapper).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

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

}

package com.example.lab6;

import java.util.EnumMap;
import java.util.Map;

public class ColorTextUtil {

    public enum AnsiColor {
        BLACK, GREEN, RED, YELLOW, RESET
    }

    private static final Map<AnsiColor, String> textColors = new EnumMap<>(AnsiColor.class);
    private static final Map<AnsiColor, String> backgroundColors = new EnumMap<>(AnsiColor.class);

    static {
        // Initialize color values
        textColors.put(AnsiColor.BLACK, "\u001B[30m");
        textColors.put(AnsiColor.GREEN, "\u001B[32m");
        textColors.put(AnsiColor.RED, "\u001B[31m");
        textColors.put(AnsiColor.YELLOW, "\u001B[33m");
        textColors.put(AnsiColor.RESET, "\u001B[0m");

        // Initialize background color values
        backgroundColors.put(AnsiColor.BLACK, "\u001B[40m");
        backgroundColors.put(AnsiColor.GREEN, "\u001B[42m");
        backgroundColors.put(AnsiColor.RED, "\u001B[41m");
        backgroundColors.put(AnsiColor.YELLOW, "\u001B[43m");
        backgroundColors.put(AnsiColor.RESET, "\u001B[0m");
    }

    public static String colorize(String input, AnsiColor textColor) {
        return textColors.get(textColor) + input + textColors.get(AnsiColor.RESET);
    }

    public static String colorize(String input, AnsiColor textColor, AnsiColor backgroundColor) {
        return backgroundColors.get(backgroundColor) + textColors.get(textColor) + input + textColors.get(AnsiColor.RESET)
                + backgroundColors.get(AnsiColor.RESET);
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println(colorize("Hello, World!", AnsiColor.GREEN));
        System.out.println(colorize("Colored Text with Background", AnsiColor.RED, AnsiColor.YELLOW));
    }
}

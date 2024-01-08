package com.example.lab8.util;

import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AnalysisUtil {
    public static <O> Function<Collection<O>, Boolean> checkRowAppearence(Predicate<O> check, int count) {
        return results -> results.stream().map(item -> check.test(item) ? 1 : 0).reduce(0,
                (counter, resultStatment) -> counter >= count + 1 ? counter : (counter + 1) * resultStatment) >= count + 1;
    }

    public static double getAverage(Collection<Double> values) {
        return getAverage(values.stream().mapToDouble(Double::doubleValue));
    }

    public static double getSum(Collection<Double> values) {
        return getSum(values.stream().mapToDouble(Double::doubleValue));
    }

    public static double getAverage(DoubleStream doubleStream) {
        return doubleStream.average().orElse(0);
    }

    public static double getSum(DoubleStream doubleStream) {
        return doubleStream.sum();
    }

    public static <O> Function<Collection<O>, Boolean> checkTrendSome(BiPredicate<O, O> biPredicate, int count) {
        return items -> checkPairsStream(items, biPredicate, count).anyMatch(Boolean::booleanValue);
    }

    public static <O> Function<Collection<O>, Boolean> checkTrendAll(BiPredicate<O, O> biPredicate, int count) {
        return items -> checkPairsStream(items, biPredicate, count).allMatch(Boolean::booleanValue);
    }

    private static <O> Stream<Boolean> checkPairsStream(Collection<O> items, BiPredicate<O, O> biPredicate, int count) {
        List<O> itemsList = items.stream().toList();
        return IntStream.range(0, itemsList.size() - count).mapToObj(i -> biPredicate.test(itemsList.get(i), itemsList.get(i + count)));
    }

}

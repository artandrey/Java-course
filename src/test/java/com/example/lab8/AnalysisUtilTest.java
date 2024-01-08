package com.example.lab8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;

import org.junit.Test;

import com.example.lab8.util.DataAnalysisUtil;

public class AnalysisUtilTest {
    @Test
    public void checkRowAppearence_TrueCondition_Success() {
        Predicate<Integer> condition = num -> num % 2 == 0;
        int count = 3;

        boolean result = DataAnalysisUtil.checkRowAppearence(condition, count).apply(Arrays.asList(2, 4, 6, 8, 10));

        assertTrue(result);
    }

    @Test
    public void checkRowAppearence_FalseCondition_Failure() {
        Predicate<Integer> condition = num -> num % 2 == 0;
        int count = 3;
        boolean result = DataAnalysisUtil.checkRowAppearence(condition, count).apply(Arrays.asList(2, 4, 6, 7, 10));

        assertFalse(result);
    }

    @Test
    public void getAverage_ValidValues_Success() {
        Collection<Double> values = Arrays.asList(2.0, 3.0, 4.0);

        double result = DataAnalysisUtil.getAverage(values);
        assertEquals(3.0, result, 0.3);
    }

    @Test
    public void getSum_ValidValues_Success() {
        Collection<Double> values = Arrays.asList(2.0, 3.0, 4.0);
        double result = DataAnalysisUtil.getSum(values);

        assertEquals(9.0, result, 0.3);
    }

    @Test
    public void getAverage_DoubleStream_Success() {
        DoubleStream doubleStream = DoubleStream.of(2.0, 3.0, 4.0);
        double result = DataAnalysisUtil.getAverage(doubleStream);

        assertEquals(3.0, result, 0.3);
    }

    @Test
    public void getSum_DoubleStream_Success() {
        DoubleStream doubleStream = DoubleStream.of(2.0, 3.0, 4.0);
        double result = DataAnalysisUtil.getSum(doubleStream);
        assertEquals(9.0, result, 0.3);
    }

    @Test
    public void checkTrendSome_TrueCondition_Success() {
        BiPredicate<Integer, Integer> condition = (a, b) -> b > a;
        int count = 2;
        boolean result = DataAnalysisUtil.checkTrendSome(condition, count).apply(Arrays.asList(1, 2, 3, 2, 3, 4));

        assertTrue(result);
    }

    @Test
    public void checkTrendSome_FalseCondition_Failure() {
        BiPredicate<Integer, Integer> condition = (a, b) -> b > a;
        int count = 2;
        boolean result = DataAnalysisUtil.checkTrendSome(condition, count).apply(Arrays.asList(3, 2, 1, 2, 1, 0));

        assertFalse(result);
    }

    @Test
    public void checkTrendAll_FalseCondition_Failure() {
        BiPredicate<Integer, Integer> condition = (a, b) -> b > a;
        int count = 2;
        boolean result = DataAnalysisUtil.checkTrendAll(condition, count).apply(Arrays.asList(1, 2, 3, 2, 3, 2));

        assertFalse(result);
    }
}

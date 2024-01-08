package com.example.lab8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.lab8.analysis.Analysis;
import com.example.lab8.entities.HourMeasurement;

public class AnalysisTest {
    @Test
    public void analyzeListofNumbers_Success() {
        List<Double> numbers = Arrays.asList(2.0, 3.0, 2.5, 4.0, 3.5, 4.5, 3.0, 2.0, 4.0, 3.5);
        Analysis<Double, String, Double> analysis = Analysis.<Double, String, Double>builder().groupBy(String::valueOf)
                .process(data -> data.stream().mapToDouble(Double::doubleValue).average().orElse(0.0)).sort(Double::compare).limitation(3)
                .build();

        Map<String, Double> result = analysis.analyze(numbers);

        assertEquals(3, result.size());

        assertEquals(2.0, result.get("2.0"));
        assertEquals(2.5, result.get("2.5"));
        assertEquals(3.0, result.get("3.0"));
    }

    @Test
    public void analyzeHourMeasurements_GroupByTime_Success() {
        List<HourMeasurement> measurements = Arrays.asList(new HourMeasurement(22.0, 0.5, 60.0, 15.0, new Date()),
                new HourMeasurement(23.0, 0.2, 55.0, 12.0, new Date()), new HourMeasurement(21.5, 0.0, 65.0, 10.0, new Date()));

        Analysis<HourMeasurement, Date, Double> analysis = Analysis.<HourMeasurement, Date, Double>builder()
                .groupBy(HourMeasurement::getTime)
                .process(data -> data.stream().mapToDouble(HourMeasurement::getTemperature).average().orElse(0.0)).sort(Double::compare)
                .build();

        Map<Date, Double> result = analysis.analyze(measurements);

        assertEquals(1, result.size());
    }
}

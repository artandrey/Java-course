package com.example.lab8;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.lab8.analysis.DataAnalysis;
import com.example.lab8.entities.Station;
import com.example.lab8.entities.WeatherData;
import com.example.lab8.service.OpenMeteoService;
import com.example.lab8.util.DataAnalysisUtil;
import com.example.lab8.util.TimeUtil;

import de.vandermeer.asciitable.AsciiTable;
import lombok.SneakyThrows;

/**
 * Hello world!
 *
 */
public class App {
    @SneakyThrows
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = dateFormat.parse("2023-01-01");
        Date toDate = dateFormat.parse("2023-12-31");

        List<WeatherData> dailyWeatherData = getWeatherDataForAllStations(fromDate, toDate);

        showTableFromMap("Raining 7 days strait table", "Station", "Is raining 7 days strait",
                getContiniousRainingStatistic(dailyWeatherData, 7));
        showTableFromMap("Raising temperature by 5 degrees C for 5 days", "Station", "Is rising by 5 degrees",
                getTemperatureContiniousRisingStatistic(dailyWeatherData, 5, 5));

        showTableFromMap("Top 10 Stations by Highest Average Temperature", "Station", "Average Temperature C",
                getTopStationsByHighestAverageTemperature(dailyWeatherData, 10));

        showTableFromMap("Top 10 Stations by Lowest Average Temperature", "Station", "Average Temperature C",
                getTopStationsByLowestAverageTemperature(dailyWeatherData, 10));

        showTableFromList("Months summary", new String[] { "Month", "Temperature", "Humidity", "Precipitation" },
                getAverageStatisticByMonth(dailyWeatherData).entrySet().stream().map(entry -> {
                    List<Object> result = new ArrayList<>();
                    result.add(entry.getKey());
                    entry.getValue().forEach(result::add);
                    return result;
                }).toList());

        showTableFromMap("Highest wind month", "Month", "Wind speed", getHighestWindMonth(dailyWeatherData));

    }

    public static Map<String, Boolean> getContiniousRainingStatistic(List<WeatherData> weatherData, int rowLength) {
        return DataAnalysis.<WeatherData, String, Boolean>builder().groupBy(weather -> weather.getId())
                .process(DataAnalysisUtil.checkRowAppearence(weather -> weather.getPrecipitationSum() > 1, rowLength)).build()
                .analyze(weatherData);
    }

    public static Map<String, Boolean> getTemperatureContiniousRisingStatistic(List<WeatherData> weatherData, int daysCount, int risedBy) {
        return DataAnalysis.<WeatherData, String, Boolean>builder().groupBy(weather -> weather.getId())
                .process(weather -> (DataAnalysisUtil
                        .<WeatherData>checkTrendSome(
                                (current, previous) -> current.getAverageTemperature() >= previous.getAverageTemperature() + 5, daysCount)
                        .apply(weather)))
                .build().analyze(weatherData);
    }

    public static Map<String, Double> getTopStationsByHighestAverageTemperature(List<WeatherData> weatherData, int limit) {
        return DataAnalysis.<WeatherData, String, Double>builder().groupBy(weather -> weather.getId())
                .process(weather -> DataAnalysisUtil.getAverage(weather.stream().mapToDouble(WeatherData::getAverageTemperature)))
                .sort(Comparator.comparingDouble(Double::doubleValue)).limitation(limit).build().analyze(weatherData);
    }

    public static Map<String, Double> getTopStationsByLowestAverageTemperature(List<WeatherData> weatherData, int limit) {
        return DataAnalysis.<WeatherData, String, Double>builder().groupBy(weather -> weather.getId())
                .process(weather -> DataAnalysisUtil.getAverage(weather.stream().mapToDouble(WeatherData::getAverageTemperature)))
                .sort(Comparator.comparingDouble(Double::doubleValue).reversed()).limitation(limit).build().analyze(weatherData);
    }

    public static Map<String, List<Double>> getAverageStatisticByMonth(List<WeatherData> weatherData) {
        return DataAnalysis.<WeatherData, String, List<Double>>builder().groupBy(weather -> TimeUtil.getMonthLiteral(weather.getDate()))
                .process(weather -> {
                    List<Double> result = new ArrayList<>();
                    result.add(DataAnalysisUtil.getAverage(weather.stream().mapToDouble(WeatherData::getAverageTemperature)));
                    result.add(DataAnalysisUtil.getAverage(weather.stream().mapToDouble(WeatherData::getAverageHumidity)));
                    result.add(DataAnalysisUtil.getAverage(weather.stream().mapToDouble(WeatherData::getPrecipitationSum)));
                    return result;
                }).build().analyze(weatherData);
    }

    public static Map<String, Double> getHighestWindMonth(List<WeatherData> weatherData) {
        return DataAnalysis.<WeatherData, String, Double>builder().groupBy(weather -> TimeUtil.getMonthLiteral(weather.getDate()))
                .process(weather -> DataAnalysisUtil.getAverage(weather.stream().mapToDouble(WeatherData::getAverageWindSpeed)))
                .sort(Comparator.comparingDouble(Double::doubleValue)).limitation(1).build().analyze(weatherData);
    }

    public static <T extends Object> String formatValue(T value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (value instanceof Double) {
            return decimalFormat.format(value);
        }
        return value.toString();
    }

    public static List<WeatherData> getWeatherDataForAllStations(Date from, Date to) {
        System.out.println("Fetching data");
        OpenMeteoService openMeteoService = new OpenMeteoService();
        return getStations().stream().map(station -> openMeteoService.getDailyWeatherData(station, from, to)).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static void showTableFromMap(String tableTitle, String keyHeading, String valueHeading,
            Map<? extends Object, ? extends Object> data) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(keyHeading, valueHeading);
        table.addRule();
        data.entrySet().forEach(dataEntry -> table.addRow(dataEntry.getKey(), formatValue(dataEntry.getValue())));
        table.addRule();
        System.out.println(tableTitle);
        System.out.println(table.render());
    }

    public static void showTableFromList(String tableTitle, String[] header, List<List<Object>> rows) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow((Object[]) header);
        table.addRule();
        rows.forEach(row -> {
            table.addRow((Object[]) row.stream().map(value -> formatValue(value)).toArray());
        });
        table.addRule();
        System.out.println(tableTitle);
        System.out.println(table.render());
    }

    public static List<Station> getStations() {
        List<Station> capitalStations = new ArrayList<>();

        capitalStations.add(new Station("Amsterdam", 4.895168, 52.370216));
        capitalStations.add(new Station("Athens", 23.727539, 37.983810));
        capitalStations.add(new Station("Belgrade", 20.448921, 44.786568));
        capitalStations.add(new Station("Berlin", 13.404954, 52.520008));
        capitalStations.add(new Station("Bern", 7.447446, 46.948020));
        capitalStations.add(new Station("Bratislava", 17.107748, 48.148598));
        capitalStations.add(new Station("Brussels", 4.351710, 50.850346));
        capitalStations.add(new Station("Bucharest", 26.102538, 44.426767));
        capitalStations.add(new Station("Budapest", 19.040235, 47.497913));
        capitalStations.add(new Station("Copenhagen", 12.568337, 55.676098));
        capitalStations.add(new Station("Dublin", -6.260310, 53.349805));
        capitalStations.add(new Station("Helsinki", 24.938379, 60.169520));
        capitalStations.add(new Station("Kyiv", 30.523400, 50.450100));
        capitalStations.add(new Station("Lisbon", -9.139337, 38.722252));
        capitalStations.add(new Station("London", -0.128002, 51.507351));
        capitalStations.add(new Station("Madrid", -3.703790, 40.416775));
        capitalStations.add(new Station("Oslo", 10.752245, 59.913869));
        capitalStations.add(new Station("Paris", 2.352222, 48.856613));
        capitalStations.add(new Station("Prague", 14.437800, 50.075538));
        capitalStations.add(new Station("Reykjavik", -21.942236, 64.146582));
        capitalStations.add(new Station("Riga", 24.105186, 56.949649));
        capitalStations.add(new Station("Rome", 12.496366, 41.902783));
        capitalStations.add(new Station("Stockholm", 18.068581, 59.329323));
        capitalStations.add(new Station("Tallinn", 24.753575, 59.437025));
        capitalStations.add(new Station("Vienna", 16.373819, 48.208174));
        capitalStations.add(new Station("Vilnius", 25.279651, 54.687156));
        capitalStations.add(new Station("Warsaw", 21.012229, 52.229676));
        capitalStations.add(new Station("Zagreb", 15.981919, 45.815011));

        return capitalStations;
    }
}

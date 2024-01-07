package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.entities.Station;
import com.example.entities.WeatherData;
import com.example.service.OpenMeteoService;

import lombok.SneakyThrows;

/**
 * Hello world!
 *
 */
public class App {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Station station = new Station("123", 52.52, 13.41);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = dateFormat.parse("2023-12-01");
        Date toDate = dateFormat.parse("2023-12-31");
        OpenMeteoService openMeteoService = new OpenMeteoService();
        openMeteoService.fetchHourlyWheaterHistory(station, fromDate, toDate);
        // WeatherData weatherData = openMeteoService.getWeatherData(station, fromDate,
        // toDate);
        // System.out.println(weatherData.getAverageHumidity());
        // openMeteoService.getWeatherDataByDays(station, fromDate,
        // toDate).forEach(System.out::println);
        ;
    }
}

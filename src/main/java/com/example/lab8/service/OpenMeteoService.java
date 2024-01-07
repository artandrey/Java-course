package com.example.lab8.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.lab8.dto.WeatherHourlyDataDTO;
import com.example.lab8.entities.DayWeatherData;
import com.example.lab8.entities.Station;
import com.example.lab8.entities.WeatherData;
import com.example.lab8.util.QueryParamsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

public class OpenMeteoService {
    private final String BASE_URL = "https://archive-api.open-meteo.com/v1/archive/";

    @SneakyThrows
    public WeatherHourlyDataDTO fetchHourlyWheaterHistory(Station station, Date from, Date to) {
        QueryParamsBuilder queryParamsBuilder = new QueryParamsBuilder();
        String queryString = queryParamsBuilder.add("latitude", station.getLatitude()).add("longitude", station.getLongitude()).add("start_date", formatDateToApiFormat(from))
                .add("end_date", formatDateToApiFormat(to)).add("hourly", "temperature_2m,precipitation,wind_speed_10m,relative_humidity_2m").build();
        URI requestUri = new URI(BASE_URL).resolve("?" + queryString);

        HttpClient httpClient = HttpClient.newHttpClient();

        System.out.println(requestUri.toString());

        HttpRequest weatherHourlyDataRequest = HttpRequest.newBuilder().uri(requestUri).GET().build();

        HttpResponse<String> weatherHourlyDataResponce = httpClient.send(weatherHourlyDataRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(weatherHourlyDataResponce.body(), WeatherHourlyDataDTO.class);
    }

    // this one
    public List<WeatherData> getDailyWeatherData(Station station, Date from, Date to) {
        WeatherHourlyDataDTO.HourlyWeather weatherHourlyData = fetchHourlyWheaterHistory(station, from, to).getHourly();
        return weatherHourlyData.getTime().stream().collect(Collectors.groupingBy(hourWeatherData -> toDateWithoutTime(hourWeatherData))).entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map((hourlyDataEntries) -> WeatherData.builder().station(station).averageTemperature(getAverage(weatherHourlyData.getTemperature()))
                        .averageHumidity(getAverage(weatherHourlyData.getRelativeHumidity())).averageWindSpeed(getAverage(weatherHourlyData.getWindSpeed()))
                        .precipitationSum(getSum(weatherHourlyData.getPrecipitation())).date(hourlyDataEntries.getKey()).build())
                .collect(Collectors.toList());
    }

    private Date toDateWithoutTime(Date date) {
        return Date.from(date.toInstant().truncatedTo(ChronoUnit.DAYS));
    }

    public WeatherData getWeatherData(Station station, Date from, Date to) {
        WeatherHourlyDataDTO.HourlyWeather weatherHourlyData = fetchHourlyWheaterHistory(station, from, to).getHourly();

        WeatherData weatherData = WeatherData.builder().station(station).averageTemperature(getAverage(weatherHourlyData.getTemperature()))
                .averageHumidity(getAverage(weatherHourlyData.getRelativeHumidity())).averageWindSpeed(getAverage(weatherHourlyData.getWindSpeed()))
                .precipitationSum(getSum(weatherHourlyData.getPrecipitation())).build();
        return weatherData;
    }

    public List<DayWeatherData> getWeatherDataByDays(Station station, Date from, Date to) {
        WeatherHourlyDataDTO.HourlyWeather weatherHourlyData = fetchHourlyWheaterHistory(station, from, to).getHourly();
        SimpleDateFormat dayDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return weatherHourlyData.getTime().stream().collect(Collectors.groupingBy(hourWeatherData -> dayDateFormat.format(hourWeatherData))).entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map((hourlyDataEntries) -> {
                    try {
                        return DayWeatherData.builder().station(station).averageTemperature(getAverage(weatherHourlyData.getTemperature()))
                                .averageHumidity(getAverage(weatherHourlyData.getRelativeHumidity())).averageWindSpeed(getAverage(weatherHourlyData.getWindSpeed()))
                                .precipitationSum(getSum(weatherHourlyData.getPrecipitation())).date(dayDateFormat.parse(hourlyDataEntries.getKey())).build();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());

    }

    private double getAverage(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    private double getSum(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static String formatDateToApiFormat(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
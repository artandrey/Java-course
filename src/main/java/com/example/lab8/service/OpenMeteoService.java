package com.example.lab8.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.lab8.analysis.Analysis;
import com.example.lab8.dto.WeatherHourlyDataDTO;

import com.example.lab8.entities.HourMeasurement;
import com.example.lab8.entities.Station;
import com.example.lab8.entities.WeatherData;
import com.example.lab8.mappers.WeatherHourlyDataMapper;
import com.example.lab8.util.QueryParamsBuilder;
import com.example.lab8.util.TimeUtil;
import lombok.SneakyThrows;

public class OpenMeteoService {
    private final String BASE_URL = "https://archive-api.open-meteo.com/v1/archive/";

    @SneakyThrows
    public WeatherHourlyDataDTO fetchHourlyWheaterHistory(Station station, Date from, Date to) {
        QueryParamsBuilder queryParamsBuilder = new QueryParamsBuilder();
        String queryString = queryParamsBuilder.add("latitude", station.getLatitude()).add("longitude", station.getLongitude())
                .add("start_date", formatDateToApiFormat(from)).add("end_date", formatDateToApiFormat(to))
                .add("hourly", "temperature_2m,precipitation,wind_speed_10m,relative_humidity_2m").build();
        URI requestUri = new URI(BASE_URL).resolve("?" + queryString);

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest weatherHourlyDataRequest = HttpRequest.newBuilder().uri(requestUri).GET().build();

        HttpResponse<String> weatherHourlyDataResponce = httpClient.send(weatherHourlyDataRequest, HttpResponse.BodyHandlers.ofString());

        WeatherHourlyDataMapper weatherHourlyDataMapper = new WeatherHourlyDataMapper();
        return weatherHourlyDataMapper.mapToWeatherHourlyDataDTO(weatherHourlyDataResponce.body());
    }

    public List<WeatherData> getDailyWeatherData(Station station, Date from, Date to) {
        List<HourMeasurement> weatherHourlyData = fetchHourlyWheaterHistory(station, from, to).getHourly().toHourMeasurements();
        return Analysis.<HourMeasurement, Date, WeatherData>builder().groupBy(hourData -> TimeUtil.toDateWithoutTime(hourData.getTime()))
                .process(hourData -> new WeatherData(hourData.stream().toList(), station)).build().analyze(weatherHourlyData).values()
                .stream().toList();
    }

    public static String formatDateToApiFormat(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
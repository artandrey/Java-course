package com.example.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherHourlyDataDTO {
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("hourly")
    private HourlyWeather hourly;

    @Data
    public static class HourlyWeather {
        @JsonProperty("time")
        private List<Date> time;
        @JsonProperty("wind_speed_10m")
        private List<Double> windSpeed;
        @JsonProperty("temperature_2m")
        private List<Double> temperature;
        @JsonProperty("relative_humidity_2m")
        private List<Double> relativeHumidity;
        @JsonProperty("precipitation")
        private List<Double> precipitation;

    }
}

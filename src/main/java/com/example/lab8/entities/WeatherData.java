package com.example.entities;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class WeatherData {
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private Station station;

    private double averageTemperature;

    private double averageHumidity;

    private double precipitationSum;

    private double averageWindSpeed;

    private Date date;

    public String getId() {
        return station.getTitle();
    }
}

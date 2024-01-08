package com.example.lab8.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HourMeasurement {
    private double temperature;
    private double precipitation;
    private double humidity;
    private double windSpeed;
    private Date time;
}

package com.example.lab8.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.lab8.util.DataAnalysisUtil;
import com.example.lab8.util.TimeUtil;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public WeatherData(List<HourMeasurement> hourMeasurements, Station station) {
        this.station = station;
        averageTemperature = DataAnalysisUtil.getAverage(hourMeasurements.stream().mapToDouble(HourMeasurement::getTemperature));
        averageHumidity = DataAnalysisUtil.getAverage(hourMeasurements.stream().mapToDouble(HourMeasurement::getHumidity));
        precipitationSum = DataAnalysisUtil.getSum(hourMeasurements.stream().mapToDouble(HourMeasurement::getPrecipitation));
        averageWindSpeed = DataAnalysisUtil.getAverage(hourMeasurements.stream().mapToDouble(HourMeasurement::getWindSpeed));
        date = TimeUtil.toDateWithoutTime(hourMeasurements.get(0).getTime());
    }

}

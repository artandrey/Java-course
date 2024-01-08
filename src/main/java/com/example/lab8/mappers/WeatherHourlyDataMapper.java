package com.example.lab8.mappers;

import com.example.lab8.dto.WeatherHourlyDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherHourlyDataMapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public WeatherHourlyDataDTO mapToWeatherHourlyDataDTO(String json) {
        try {
            return objectMapper.readValue(json, WeatherHourlyDataDTO.class);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

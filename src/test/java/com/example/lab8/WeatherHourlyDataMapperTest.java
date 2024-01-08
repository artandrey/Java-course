package com.example.lab8;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import com.example.lab8.dto.WeatherHourlyDataDTO;
import com.example.lab8.mappers.WeatherHourlyDataMapper;

public class WeatherHourlyDataMapperTest {
    WeatherHourlyDataMapper weatherHourlyDataMapper;

    @Before
    public void setUp() {
        weatherHourlyDataMapper = new WeatherHourlyDataMapper();
    }

    @Test
    public void mapToWeatherHourlyDataDTO_ValidJson_ReturnsDTO() {
        String json = """
                {
                    "latitude": 52.337433,
                    "longitude": 4.8333335,
                    "hourly": {
                        "time": [
                            "2023-12-30T00:00",
                            "2023-12-30T01:00",
                            "2023-12-30T02:00"
                        ],
                        "temperature_2m": [
                            7.4, 7.5, 7.4
                        ],
                        "relative_humidity_2m": [
                            75, 70, 69
                        ],
                        "precipitation": [
                            0.0, 0.0, 0.0
                        ],
                        "wind_speed_10m": [
                            27.1, 26.2, 25.1
                        ]
                    }
                }

                    """;

        WeatherHourlyDataDTO weatherHourlyDataDTO = weatherHourlyDataMapper.mapToWeatherHourlyDataDTO(json);

        assertNotNull(weatherHourlyDataDTO);
        assertNotNull(weatherHourlyDataDTO.getLatitude());
        assertNotNull(weatherHourlyDataDTO.getLongitude());
        assertNotNull(weatherHourlyDataDTO.getHourly());

        WeatherHourlyDataDTO.HourlyWeather hourlyWeather = weatherHourlyDataDTO.getHourly();
        assertNotNull(hourlyWeather.getTime());
        assertNotNull(hourlyWeather.getWindSpeed());
        assertNotNull(hourlyWeather.getTemperature());
        assertNotNull(hourlyWeather.getRelativeHumidity());
        assertNotNull(hourlyWeather.getPrecipitation());
    }

    @Test
    public void mapToWeatherHourlyDataDTO_InvalidJson_ThrowsRuntimeException() {
        String invalidJson = "invalid JSON string";

        assertThrows(RuntimeException.class, () -> weatherHourlyDataMapper.mapToWeatherHourlyDataDTO(invalidJson));
    }

    @Test
    public void mapToWeatherHourlyDataDTO_NullJson_ThrowsRuntimeException() {
        String nullJson = null;

        assertThrows(RuntimeException.class, () -> weatherHourlyDataMapper.mapToWeatherHourlyDataDTO(nullJson));
    }

    @Test
    public void mapToWeatherHourlyDataDTO_InvalidJsonData_ThrowsRuntimeException() {
        String invalidJson = "{invalid: 1}";

        assertThrows(RuntimeException.class, () -> weatherHourlyDataMapper.mapToWeatherHourlyDataDTO(invalidJson));
    }

}

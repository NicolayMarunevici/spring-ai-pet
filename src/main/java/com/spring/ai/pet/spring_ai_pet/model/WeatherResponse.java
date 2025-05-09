package com.spring.ai.pet.spring_ai_pet.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.math.BigDecimal;

public record WeatherResponse(
    @JsonPropertyDescription("WindSpeedin KHM") BigDecimal windSpeed,
    @JsonPropertyDescription("Direction of wind") Integer windDegrees,
    @JsonPropertyDescription("Currebt Temperature in Celsius") Integer temp,
    @JsonPropertyDescription("Current humidity") Integer humidity,
    @JsonPropertyDescription("Epoch time of sunset GMT ") Integer sunset,
    @JsonPropertyDescription("Epoch time of Sunrise GMT ") Integer sunrise,
    @JsonPropertyDescription("Low Temperature in Celsius") Integer minTemp,
    @JsonPropertyDescription("Cloud Coverage Percentage") Integer cloudPct,
    @JsonPropertyDescription("Temperature in Celsius") Integer feelsLike,
    @JsonPropertyDescription("MaximumTemperature in Celsius") Integer maxTemp
) {
}

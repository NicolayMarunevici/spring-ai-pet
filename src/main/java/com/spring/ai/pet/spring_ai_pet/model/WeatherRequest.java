package com.spring.ai.pet.spring_ai_pet.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Weather API request")
public record WeatherRequest(
    @JsonProperty(required = true, value = "location") @JsonPropertyDescription("The city and state") String location,
    @JsonProperty(required = false) @JsonPropertyDescription("Optional state name") String state,
    @JsonProperty(required = false) @JsonPropertyDescription("Optional country name") String country) {
}

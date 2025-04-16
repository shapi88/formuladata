package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Driver(
        @JsonProperty("driver_number") Integer driverNumber,
        @JsonProperty("full_name") String fullName,
        @JsonProperty("team_name") String teamName,
        @JsonProperty("team_colour") String teamColour,
        @JsonProperty("country_code") String countryCode
) {}
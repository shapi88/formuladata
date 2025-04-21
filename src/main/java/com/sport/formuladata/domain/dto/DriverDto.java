package com.sport.formuladata.domain.dto;

public record DriverDto(
        Integer driverNumber,
        String fullName,
        String teamName,
        String teamColour,
        String countryCode
) {}
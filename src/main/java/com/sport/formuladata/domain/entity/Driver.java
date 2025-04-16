package com.sport.formuladata.domain.entity;

public record Driver(
        Integer driverNumber,
        String fullName,
        String teamName,
        String teamColour,
        String countryCode
) {}
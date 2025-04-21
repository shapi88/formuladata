package com.sport.formuladata.domain.dto;

public record LapDto(
        Integer lapId,
        Integer lapNumber,
        Float lapDuration,
        DriverDto driver
) {}
package com.sport.formuladata.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sport.formuladata.domain.entities.Country;

public record DriverDto(
    String id,
    String name,
    String firstName,
    String lastName,
    String fullName,
    String abbreviation,
    String permanentNumber,
    String gender,
    LocalDate dateOfBirth,
    LocalDate dateOfDeath,
    String placeOfBirth,
    Country countryOfBirth,
    Country nationality,
    Country secondNationality,
    Integer bestChampionshipPosition,
    Integer bestStartingGridPosition,
    Integer bestRaceResult,
    Integer totalChampionshipWins,
    Integer totalRaceEntries,
    Integer totalRaceStarts,
    Integer totalRaceWins,
    Integer totalRaceLaps,
    Integer totalPodiums,
    BigDecimal totalPoints,
    BigDecimal totalChampionshipPoints,
    Integer totalPolePositions,
    Integer totalFastestLaps,
    Integer totalDriverOfTheDay,
    Integer totalGrandSlams
) {}
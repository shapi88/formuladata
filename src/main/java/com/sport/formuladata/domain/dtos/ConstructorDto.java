package com.sport.formuladata.domain.dtos;

import java.math.BigDecimal;

import com.sport.formuladata.domain.entities.Country;

public record ConstructorDto(
    String id,
    String name,
    String fullName,
    Country country,
    Integer bestChampionshipPosition,
    Integer bestStartingGridPosition,
    Integer bestRaceResult,
    Integer totalChampionshipWins,
    Integer totalRaceEntries,
    Integer totalRaceStarts,
    Integer totalRaceWins,
    Integer total1And2Finishes,
    Integer totalRaceLaps,
    Integer totalPodiums,
    Integer totalPodiumRaces,
    BigDecimal totalPoints,
    BigDecimal totalChampionshipPoints,
    Integer totalPolePositions,
    Integer totalFastestLaps
) {}
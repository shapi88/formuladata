package com.sport.formuladata.domain.dtos;

import java.math.BigDecimal;

public record ConstructorDto(
    String id,
    String name,
    String fullName,
    String countryId,
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
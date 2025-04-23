package com.sport.formuladata.domain.dtos;

public record GrandPrixDto(
        String id,
        String name,
        String fullName,
        String shortName,
        String abbreviation,
        //String countryId, // todo
        Integer totalRacesHeld
    ) {
}

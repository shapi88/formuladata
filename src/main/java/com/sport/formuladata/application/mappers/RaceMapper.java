package com.sport.formuladata.application.mappers;

import com.sport.formuladata.domain.dtos.RaceDto;
import com.sport.formuladata.domain.entities.Race;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    @Mapping(target = "grandPrixId", source = "grandPrix.id")
    @Mapping(target = "circuitId", source = "circuit.id")
    RaceDto toDto(Race race);

    @Mapping(target = "grandPrix", ignore = true)
    @Mapping(target = "circuit", ignore = true)
    Race toEntity(RaceDto raceDto);

    @Mapping(target = "grandPrix", ignore = true)
    @Mapping(target = "circuit", ignore = true)
    void updateEntity(RaceDto raceDto, @MappingTarget Race race);
}
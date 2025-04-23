package com.sport.formuladata.application.mappers;

import com.sport.formuladata.domain.dtos.RaceDataDto;
import com.sport.formuladata.domain.entities.RaceData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RaceDataMapper {
    @Mapping(target = "driver", source = "driver")
    @Mapping(target = "constructor", source = "constructor")
    //@Mapping(target = "circuitId", source = "race.circuit")
    //@Mapping(target = "engineManufacturerId", source = "engineManufacturer.id")
    //@Mapping(target = "tyreManufacturerId", source = "tyreManufacturer.id")
    RaceDataDto toDto(RaceData raceData);

    //Unmapped target property: "circuitId". 
    //Mapping from property "Race race" to "RaceDto race"

    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "constructor", ignore = true)
    //@Mapping(target = "raceId", ignore = true)
    // @Mapping(target = "engineManufacturer", ignore = true)
    // @Mapping(target = "tyreManufacturer", ignore = true)
    RaceData toEntity(RaceDataDto raceDataDto);

    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "constructor", ignore = true)
    //@Mapping(target = "raceId", ignore = true)
    // @Mapping(target = "engineManufacturer", ignore = true)
    // @Mapping(target = "tyreManufacturer", ignore = true)
    void updateEntity(RaceDataDto raceDataDto, @MappingTarget RaceData raceData);
}
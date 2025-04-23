package com.sport.formuladata.application.mappers;

import com.sport.formuladata.domain.dtos.RaceDataDto;
import com.sport.formuladata.domain.entities.RaceData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {RaceMapper.class, DriverMapper.class, ConstructorMapper.class})
public interface RaceDataMapper {
    @Mapping(target = "race", source = "race")
    @Mapping(target = "driver", source = "driver")
    @Mapping(target = "constructor", source = "constructor")
    RaceDataDto toDto(RaceData raceData);

    @Mapping(target = "raceId", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "constructor", ignore = true)
    RaceData toEntity(RaceDataDto raceDataDto);

    @Mapping(target = "raceId", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "constructor", ignore = true)
    void updateEntity(RaceDataDto raceDataDto, @MappingTarget RaceData raceData);
}
package com.sport.formuladata.application.mappers;

import com.sport.formuladata.domain.dtos.DriverDto;
import com.sport.formuladata.domain.entities.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    @Mapping(target = "countryOfBirth", source = "countryOfBirth")
    @Mapping(target = "nationality", source = "nationality")
    @Mapping(target = "secondNationality", source = "secondNationality")
    DriverDto toDto(Driver driver);

    @Mapping(target = "countryOfBirth", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "secondNationality", ignore = true)
    Driver toEntity(DriverDto driverDto);

    @Mapping(target = "countryOfBirth", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "secondNationality", ignore = true)
    void updateEntity(DriverDto driverDto, @MappingTarget Driver driver);
}
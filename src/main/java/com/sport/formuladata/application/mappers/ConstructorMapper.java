package com.sport.formuladata.application.mappers;

import com.sport.formuladata.domain.dtos.ConstructorDto;
import com.sport.formuladata.domain.entities.Constructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConstructorMapper {
    @Mapping(target = "countryId", source = "country.id")
    ConstructorDto toDto(Constructor constructor);

    @Mapping(target = "country", ignore = true)
    Constructor toEntity(ConstructorDto constructorDto);

    @Mapping(target = "country", ignore = true)
    void updateEntity(ConstructorDto constructorDto, @MappingTarget Constructor constructor);
}
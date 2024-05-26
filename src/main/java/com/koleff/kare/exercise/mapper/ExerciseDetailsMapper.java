package com.koleff.kare.exercise.mapper;

import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExerciseDetailsMapper {
    public ExerciseDetailsMapper INSTANCE = Mappers.getMapper( ExerciseDetailsMapper.class );

    @Mapping(source = "exerciseDetailsId", target = "id")
    ExerciseDetailsDto toDto(ExerciseDetails exerciseDetails);

    @Mapping(source = "id", target = "exerciseDetailsId")
    ExerciseDetails toEntity(ExerciseDetailsDto exerciseDetailsDto);
}

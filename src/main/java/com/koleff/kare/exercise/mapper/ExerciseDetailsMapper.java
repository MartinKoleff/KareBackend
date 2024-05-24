package com.koleff.kare.exercise.mapper;

import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseDetailsMapper {
    ExerciseDetailsDto toDto(ExerciseDetails exerciseDetails);

    ExerciseDetails toEntity(ExerciseDetailsDto exerciseDetailsDto);
}

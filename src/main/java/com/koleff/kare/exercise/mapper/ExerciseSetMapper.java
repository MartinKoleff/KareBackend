package com.koleff.kare.exercise.mapper;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseSetMapper {

    @Mapping(source = "exerciseSetId", target = "id")
    ExerciseSetDto toDto(ExerciseSet exerciseSet);

    @Mapping(source = "id", target = "exerciseSetId")
    ExerciseSet toEntity(ExerciseSetDto exerciseSetDto);
}

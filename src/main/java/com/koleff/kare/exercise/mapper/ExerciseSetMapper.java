package com.koleff.kare.exercise.mapper;

import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseSetMapper {

    @Mapping(source = "exercise.exerciseId", target = "exerciseId")
    @Mapping(source = "exercise.workoutId", target = "workoutId")
    ExerciseSetDto toDto(ExerciseSet exerciseSet);

    @Mapping(source = "id", target = "exerciseSetId")
    ExerciseSet toEntity(ExerciseSetDto exerciseSetDto);
}

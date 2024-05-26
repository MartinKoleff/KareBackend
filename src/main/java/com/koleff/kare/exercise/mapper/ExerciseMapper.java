package com.koleff.kare.exercise.mapper;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ExerciseSetMapper.class)
public interface ExerciseMapper {
    @Mapping(source = "sets", target = "sets")
    @Mapping(source = "exerciseId", target = "id")
    ExerciseDto toDto(Exercise exercise);

    @Mapping(source = "sets", target = "sets")
    @Mapping(source = "id", target = "exerciseId")
    Exercise toEntity(ExerciseDto exerciseDto);
}

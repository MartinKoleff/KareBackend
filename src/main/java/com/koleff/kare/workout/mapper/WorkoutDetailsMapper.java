package com.koleff.kare.workout.mapper;

import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExerciseMapper.class, WorkoutConfigurationMapper.class})
public interface WorkoutDetailsMapper {
    @Mapping(source = "exercises", target = "exercises")
    @Mapping(source = "configuration", target = "configuration")
    WorkoutDetailsDto toDto(WorkoutDetails workoutDetails);

    @Mapping(source = "exercises", target = "exercises")
    @Mapping(source = "configuration", target = "configuration")
    WorkoutDetails toEntity(WorkoutDetailsDto workoutDetailsDto);
}
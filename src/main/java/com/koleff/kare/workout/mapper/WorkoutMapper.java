package com.koleff.kare.workout.mapper;

import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WorkoutDetailsMapper.class)
public interface WorkoutMapper {
    @Mapping(source = "workoutDetails", target = "workoutDetails")
    WorkoutDto toDto(Workout workout);

    @Mapping(source = "workoutDetails", target = "workoutDetails")
    Workout toEntity(WorkoutDto workoutDto);
}
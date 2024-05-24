package com.koleff.kare.workout.mapper;

import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutConfigurationMapper {
    WorkoutConfigurationDto toDto(WorkoutConfiguration workoutConfiguration);
    WorkoutConfiguration toEntity(WorkoutConfigurationDto workoutConfigurationDto);
}
package com.koleff.kare.workout.mapper;

import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutConfigurationMapper {
    @Mapping(source = "workoutConfigurationId", target = "id")
    WorkoutConfigurationDto toDto(WorkoutConfiguration workoutConfiguration);

    @Mapping(source = "id", target = "workoutConfigurationId")
    WorkoutConfiguration toEntity(WorkoutConfigurationDto workoutConfigurationDto);
}
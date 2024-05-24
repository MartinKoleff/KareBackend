package com.koleff.kare.workout.models.dto;

import com.koleff.kare.exercise.models.dto.ExerciseDto;

import java.util.List;

public record WorkoutDetailsDto(
    Long id,
    String name,
    String description,
    Integer muscleGroupId,
    Boolean isFavorite,
    List<ExerciseDto> exercises,
    WorkoutConfigurationDto configuration
) {}

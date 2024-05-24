package com.koleff.kare.workout.models.dto;

public record WorkoutConfigurationDto(
    Long id,
    Long workoutDetailsId,
    String cooldownTime
) {}

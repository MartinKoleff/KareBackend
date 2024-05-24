package com.koleff.kare.exercise.models.dto;

import java.util.UUID;

public record ExerciseSetDto(
    UUID id,
    Integer number,
    Long exerciseId,
    Long workoutId,
    Integer reps,
    Float weight
) {}

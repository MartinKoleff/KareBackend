package com.koleff.kare.exercise.models.dto;

import java.util.UUID;

public record ExerciseSetDto(
    String id,
    Integer number,
    Long exerciseId,
    Long workoutId,
    Integer reps,
    Float weight
) {}

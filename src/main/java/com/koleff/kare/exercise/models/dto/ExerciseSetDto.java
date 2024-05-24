package com.koleff.kare.exercise.models.dto;

import java.util.UUID;

public record ExerciseSetDto(
    UUID id,
    Integer number,
    Integer exerciseId,
    Integer workoutId,
    Integer reps,
    Float weight
) {}

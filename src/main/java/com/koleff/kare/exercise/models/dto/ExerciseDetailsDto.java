package com.koleff.kare.exercise.models.dto;

import java.util.List;

public record ExerciseDetailsDto(
        Long id,
        Long workoutId,
        String name,
        String description,
        Integer muscleGroupId,
        Integer machineTypeId,
        String snapshot,
        String videoUrl
) {
}

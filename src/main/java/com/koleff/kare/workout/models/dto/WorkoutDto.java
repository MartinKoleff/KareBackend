package com.koleff.kare.workout.models.dto;

import java.util.List;

public record WorkoutDto(
    Long id,
    String name,
    Integer muscleGroupId,
    String snapshot,
    Integer totalExercises,
    Boolean isFavorite,
    List<WorkoutDetailsDto> workoutDetails
) {}

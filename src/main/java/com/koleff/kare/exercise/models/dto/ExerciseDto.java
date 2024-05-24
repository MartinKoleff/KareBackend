package com.koleff.kare.exercise.models.dto;

import java.util.List;

public record ExerciseDto(
    Long id,
    String name,
    Integer muscleGroupId,
    Integer machineTypeId,
    String snapshot,
    List<ExerciseSetDto> sets
) {}

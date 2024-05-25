package com.koleff.kare.do_workout_performance_metrics.models.dto;

import java.util.Date;
import java.util.List;

public record DoWorkoutPerformanceMetricsDto(
        Long id, //TODO: migrate to UUID in future...
        Long workoutId,
        Date date,
        List<DoWorkoutExerciseSetDto> doWorkoutExerciseSets
) {
}

package com.koleff.kare.do_workout_performance_metrics.models.dto;

import com.koleff.kare.do_workout_performance_metrics.models.entity.ExerciseTime;

import java.util.Date;
import java.util.UUID;

public record DoWorkoutExerciseSetDto(
        String instanceId,
        Long workoutPerformanceMetricsId,
        Long workoutId,
        Long exerciseId,
        String templateSetId,
        Integer reps,
        Float weight,
        Boolean isDone,
        ExerciseTime time,
        Date date
) {
}
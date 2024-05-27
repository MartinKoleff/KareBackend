package com.koleff.kare.do_workout_performance_metrics.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record DoWorkoutPerformanceMetricsDto(

        @JsonProperty("id")
        Long id, //TODO: migrate to UUID in future...

        @JsonProperty("workout_id")
        Long workoutId,

        @JsonProperty("date")
        Date date,

        @JsonProperty("do_workout_exercise_sets")
        List<DoWorkoutExerciseSetDto> doWorkoutExerciseSets
) {
}

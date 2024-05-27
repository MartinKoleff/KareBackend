package com.koleff.kare.do_workout_performance_metrics.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koleff.kare.do_workout_performance_metrics.models.entity.ExerciseTime;

import java.util.Date;
import java.util.UUID;

public record DoWorkoutExerciseSetDto(

        @JsonProperty("instance_id")
        String instanceId,

        @JsonProperty("workout_performance_metrics_ic")
        Long workoutPerformanceMetricsId,

        @JsonProperty("workout_id")
        Long workoutId,

        @JsonProperty("exercise_id")
        Long exerciseId,

        @JsonProperty("template_set_id")
        String templateSetId,

        @JsonProperty("reps")
        Integer reps,

        @JsonProperty("weight")
        Float weight,

        @JsonProperty("is_done")
        Boolean isDone,

        @JsonProperty("time")
        ExerciseTime time,

        @JsonProperty("date")
        Date date
) {
}
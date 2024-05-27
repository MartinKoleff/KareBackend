package com.koleff.kare.exercise.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ExerciseSetDto(

        @JsonProperty("id")
        String id,

        @JsonProperty("number")
        Integer number,

        @JsonProperty("exercise_id")
        Long exerciseId,

        @JsonProperty("workout_id")
        Long workoutId,

        @JsonProperty("reps")
        Integer reps,

        @JsonProperty("weight")
        Float weight
) {
}

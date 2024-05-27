package com.koleff.kare.exercise.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExerciseDto(

        @JsonProperty("id")
        Long id,

        @JsonProperty("workout_id")
        Long workoutId,

        @JsonProperty("name")
        String name,

        @JsonProperty("muscle_group_id")
        Integer muscleGroupId,

        @JsonProperty("machine_type_id")
        Integer machineTypeId,

        @JsonProperty("snapshot")
        String snapshot,

        @JsonProperty("exercise_sets")
        List<ExerciseSetDto> sets
) {
}

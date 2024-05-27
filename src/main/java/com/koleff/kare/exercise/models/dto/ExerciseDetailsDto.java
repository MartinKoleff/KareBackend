package com.koleff.kare.exercise.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public record ExerciseDetailsDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("workout_id")
        Long workoutId,

        @JsonProperty("name")
        String name,

        @JsonProperty("description")
        String description,

        @JsonProperty("muscle_group_id")
        Integer muscleGroupId,

        @JsonProperty("machine_type_id")
        Integer machineTypeId,

        @JsonProperty("snapshot")
        String snapshot,

        @JsonProperty("video_url")
        String videoUrl
) {
}

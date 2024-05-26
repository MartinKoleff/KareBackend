package com.koleff.kare.workout.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WorkoutDto(

        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("muscle_group_id")
        Integer muscleGroupId,

        @JsonProperty("snapshot")
        String snapshot,

        @JsonProperty("total_exercises")
        Integer totalExercises,

        @JsonProperty("is_favorite")
        Boolean isFavorite,

        @JsonProperty("workout_details")
        List<WorkoutDetailsDto> workoutDetails
) {
}

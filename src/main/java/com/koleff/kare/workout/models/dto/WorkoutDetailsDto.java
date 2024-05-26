package com.koleff.kare.workout.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koleff.kare.exercise.models.dto.ExerciseDto;

import java.util.List;

public record WorkoutDetailsDto(

        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("description")
        String description,

        @JsonProperty("muscle_group_id")
        Integer muscleGroupId,

        @JsonProperty("is_favorite")
        Boolean isFavorite,

        @JsonProperty("exercises")
        List<ExerciseDto> exercises,

        @JsonProperty("configuration")
        WorkoutConfigurationDto configuration
) {
}

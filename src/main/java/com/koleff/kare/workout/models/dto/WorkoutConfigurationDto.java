package com.koleff.kare.workout.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WorkoutConfigurationDto(

        @JsonProperty("id")
        Long id,

        @JsonProperty("workout_details_id")
        Long workoutDetailsId,

        @JsonProperty("cooldown_time")
        String cooldownTime
) {
}

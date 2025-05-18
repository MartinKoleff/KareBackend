package com.koleff.kare.statistics.workout.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record WeightProgression(
        @JsonProperty("weight")
        Float weight,

        @JsonProperty("date")
        Date date
) {
}

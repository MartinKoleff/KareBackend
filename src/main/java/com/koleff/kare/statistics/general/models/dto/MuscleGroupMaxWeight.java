
package com.koleff.kare.statistics.general.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koleff.kare.exercise.models.entity.MuscleGroup;

import java.util.Date;
import java.util.List;

public record MuscleGroupMaxWeight(

        @JsonProperty("muscle_group")
        MuscleGroup muscleGroup,

        @JsonProperty("max_weight")
        Float maxWeight
) {
}

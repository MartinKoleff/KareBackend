
package com.koleff.kare.statistics.general.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;

import java.util.Date;
import java.util.List;

public record WorkoutTotalTimesCompleted(

        @JsonProperty("total_times_completed")
        Integer totalTimesCompleted,

        @JsonProperty("dates_of_completion")
        List<Date> datesOfCompletion
) {
}

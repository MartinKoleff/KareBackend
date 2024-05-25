package com.koleff.kare.workout.models.request;

import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.dto.WorkoutDto;

public record UpdateWorkoutRequest(WorkoutDto workout) {
}

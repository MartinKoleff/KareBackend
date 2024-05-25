package com.koleff.kare.workout.models.request;

import java.util.List;

public record MultipleExercisesDeletionRequest(Long workoutId, List<Long> exerciseIds) {
}

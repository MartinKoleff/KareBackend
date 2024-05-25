package com.koleff.kare.workout.models.request;

import com.koleff.kare.exercise.models.dto.ExerciseDto;

public record ExerciseAddRequest(Long workoutId, ExerciseDto exercise) {
}

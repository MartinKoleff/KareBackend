package com.koleff.kare.workout.models.request;

import com.koleff.kare.exercise.models.dto.ExerciseDto;

import java.util.List;

public record MultipleExercisesUpdateRequest(Long workoutId, List<ExerciseDto> exerciseList) {
}

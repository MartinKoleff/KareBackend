package com.koleff.kare.exercise.models.request;

import com.koleff.kare.exercise.models.dto.ExerciseSetDto;

import java.util.List;
import java.util.UUID;

public record AddNewExerciseSetRequest(Long exerciseId, Long workoutId, List<ExerciseSetDto> currentSets) {
}
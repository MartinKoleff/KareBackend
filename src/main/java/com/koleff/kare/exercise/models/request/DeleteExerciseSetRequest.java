package com.koleff.kare.exercise.models.request;

import java.util.UUID;

public record DeleteExerciseSetRequest(Long exerciseId, Long workoutId, UUID setId) {
}
package com.koleff.kare.exercise.models.request;

import java.util.UUID;

public record AddNewExerciseSetRequest(Long exerciseId, Integer workoutId, UUID setId) {
}
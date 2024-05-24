package com.koleff.kare.exercise.models.response;

import com.koleff.kare.exercise.models.entity.Exercise;

import java.util.List;

public record ExerciseListResponse(List<Exercise> exercises) {
}
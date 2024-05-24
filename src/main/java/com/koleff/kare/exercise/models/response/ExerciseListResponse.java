package com.koleff.kare.exercise.models.response;

import com.koleff.kare.exercise.models.dto.ExerciseDto;

import java.util.List;

public record ExerciseListResponse(List<ExerciseDto> exercises) {
}
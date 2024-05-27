package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;

import java.util.List;
import java.util.UUID;

public interface ExerciseConfigureService {

    ExerciseDto addNewExerciseSet(Long exerciseId, Long workoutId, List<ExerciseSetDto> currentSets);
    ExerciseDto deleteExerciseSet(Long exerciseId, Long workoutId, UUID setId);
}

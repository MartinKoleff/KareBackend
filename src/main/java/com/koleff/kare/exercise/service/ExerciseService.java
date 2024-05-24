package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseSet;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> getCatalogExercises(Integer muscleGroupId);
    List<ExerciseDto> getAllCatalogExercises();
    List<ExerciseDto> getAllExercises();
    ExerciseDto getExercise(Long exerciseId, Long workoutId);
    ExerciseDto saveExercise(ExerciseDto exercise);
    ExerciseDto updateExercise(ExerciseDto exercise);
    void deleteExercise(ExerciseDto exercise);
}

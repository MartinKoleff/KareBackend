package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.dto.ExerciseDto;

import java.util.List;

public interface ExerciseDetailsService {

    ExerciseDetailsDto getExerciseDetails(Long exerciseId, Long workoutId);
    ExerciseDetailsDto saveExerciseDetails(ExerciseDetailsDto exerciseDetails);
    ExerciseDetailsDto updateExerciseDetails(ExerciseDetailsDto exerciseDetails);
    void deleteExerciseDetails(ExerciseDetailsDto exerciseDetails);
    void deleteExerciseDetails(Long exerciseId, Long workoutId);
}

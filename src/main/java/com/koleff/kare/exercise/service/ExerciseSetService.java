package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.exercise.models.entity.ExerciseSet;

import java.util.List;
import java.util.UUID;

public interface ExerciseSetService {

    ExerciseSetDto getSetById(UUID exerciseSetId);
    ExerciseSetDto saveExerciseSet(ExerciseSetDto exerciseSet);
    List<ExerciseSetDto> saveAllExerciseSets(List<ExerciseSetDto> exerciseSets);
    void updateExerciseSet(ExerciseSetDto exerciseSet);
    void deleteExerciseSet(ExerciseSetDto exerciseSet);
    void deleteSetById(UUID exerciseSetId);
}

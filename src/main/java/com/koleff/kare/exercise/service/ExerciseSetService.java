package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.dto.ExerciseSetDto;

import java.util.List;

public interface ExerciseSetService {

    ExerciseSetDto getSetById(String exerciseSetId);
    ExerciseSetDto saveExerciseSet(ExerciseSetDto exerciseSet);
    List<ExerciseSetDto> saveAllExerciseSets(List<ExerciseSetDto> exerciseSets);
    void updateExerciseSet(ExerciseSetDto exerciseSet);
    void deleteExerciseSet(ExerciseSetDto exerciseSet);
    void deleteSetById(String exerciseSetId);
}

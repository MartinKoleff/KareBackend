package com.koleff.kare.do_workout_performance_metrics.services;

import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutExerciseSetDto;

import java.util.List;

public interface DoWorkoutExerciseSetService {

    void saveDoWorkoutExerciseSet(DoWorkoutExerciseSetDto exerciseSet);
    void saveAllDoWorkoutExerciseSet(List<DoWorkoutExerciseSetDto> exerciseSets);
}

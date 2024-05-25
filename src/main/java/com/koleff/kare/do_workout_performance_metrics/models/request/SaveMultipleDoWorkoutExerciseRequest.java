package com.koleff.kare.do_workout_performance_metrics.models.request;

import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutExerciseSetDto;

import java.util.List;

public record SaveMultipleDoWorkoutExerciseRequest(List<DoWorkoutExerciseSetDto> doWorkoutExerciseSetList) {
}

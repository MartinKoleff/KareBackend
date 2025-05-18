package com.koleff.kare.statistics.workout.service;

import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;

import java.util.Date;
import java.util.List;

public interface WorkoutStatisticsService {

    WorkoutTotalTimesCompleted getTotalTimesCompleted(Long workoutId);

    Float getTotalWeightLifted(Long workoutId);

    List<Date> getDatesOfCompletionForWorkout(Long workoutId);

   Integer getTotalRepsPerformed(Long workoutId);

   Integer getTotalSetsPerformed(Long workoutId);
}

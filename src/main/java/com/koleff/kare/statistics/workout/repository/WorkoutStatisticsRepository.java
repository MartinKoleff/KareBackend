package com.koleff.kare.statistics.workout.repository;

import com.koleff.kare.statistics.workout.models.dto.WeightProgression;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkoutStatisticsRepository  {

    @Query("SELECT COUNT(d) FROM DoWorkoutPerformanceMetrics d WHERE d.workoutId = :workoutId")
    int getTotalWorkoutsCompleted(@Param("workoutId") Long workoutId);

    @Query("SELECT d.date FROM DoWorkoutPerformanceMetrics d WHERE d.workoutId = :workoutId")
    List<Date> getDatesOfCompletionForWorkout(@Param("workoutId") Long workoutId);

    @Query("SELECT MAX(d.weight) FROM DoWorkoutExerciseSet d WHERE d.exerciseId = :exerciseId")
    Float getMaxWeightForExercise(@Param("exerciseId") Long exerciseId);

    @Query("SELECT d.weight AS weight, d.date AS date FROM DoWorkoutExerciseSet d WHERE d.exerciseId = :exerciseId ORDER BY d.date ASC")
    List<WeightProgression> getWeightProgression(@Param("exerciseId") Long exerciseId);

    @Query("SELECT SUM(d.reps) FROM DoWorkoutExerciseSet d WHERE d.workoutId = :workoutId")
    Integer getTotalRepsPerformedForWorkout(@Param("workoutId") Long workoutId);

    @Query("SELECT COUNT(d) FROM DoWorkoutExerciseSet d WHERE d.workoutId = :workoutId")
    Integer getTotalSetsPerformedForWorkout(@Param("workoutId") Long workoutId);

    @Query("SELECT SUM(d.reps * d.weight) FROM DoWorkoutExerciseSet d WHERE d.workoutId = :workoutId")
    Float getTotalWeightLiftedForWorkout(@Param("workoutId") Long workoutId);
}

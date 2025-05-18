package com.koleff.kare.statistics.general.repository;

import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutExerciseSet;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.workout.models.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralStatisticsRepository {
    @Query("SELECT COUNT(d) FROM DoWorkoutPerformanceMetrics d")
    int getTotalWorkoutsCompleted();

    @Query("SELECT COUNT(DISTINCT s.exerciseId) FROM DoWorkoutExerciseSet s")
    int getTotalExercisesCount();

    @Query("SELECT COUNT(s) FROM DoWorkoutExerciseSet s")
    int getTotalSetsCount();

    @Query("SELECT SUM(s.reps) FROM DoWorkoutExerciseSet s")
    Integer getTotalRepsCount();

    @Query("SELECT SUM(s.reps * s.weight) FROM DoWorkoutExerciseSet s")
    Float getTotalWeightLifted();

    @Query("SELECT MAX(s.weight) FROM DoWorkoutExerciseSet s WHERE s.reps = 1")
    Float getBestPR();

    @Query("""
                SELECT w FROM Workout w 
                WHERE w.workoutId = (
                    SELECT d.workoutId FROM DoWorkoutPerformanceMetrics d 
                    GROUP BY d.workoutId 
                    ORDER BY COUNT(d.workoutId) DESC 
                    LIMIT 1
                )
            """)
    Workout getFavoriteWorkout();
}

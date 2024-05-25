package com.koleff.kare.do_workout_performance_metrics.repository;

import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutExerciseSet;
import com.koleff.kare.do_workout_performance_metrics.models.entity.ExerciseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface DoWorkoutExerciseSetRepository extends JpaRepository<DoWorkoutExerciseSet, UUID> {

    @Query("SELECT s FROM DoWorkoutExerciseSet s WHERE s.doWorkoutPerformanceMetricsId = :doWorkoutPerformanceMetricsId")
    List<DoWorkoutExerciseSet> findDoWorkoutExerciseSetByDoWorkoutPerformanceMetricsId(Long doWorkoutPerformanceMetricsId);

    @Query("SELECT s FROM DoWorkoutExerciseSet s WHERE s.instanceId = :instanceId")
    DoWorkoutExerciseSet findDoWorkoutExerciseSetByInstanceId(UUID instanceId);

    @Query("SELECT s FROM DoWorkoutExerciseSet s WHERE s.workoutId = :workoutId")
    List<DoWorkoutExerciseSet> findDoWorkoutExerciseSetByWorkoutId(Long workoutId);

    @Modifying
    @Transactional
    @Query("DELETE FROM DoWorkoutExerciseSet s WHERE s.instanceId = :instanceId")
    void deleteByInstanceId(UUID instanceId);

    @Modifying
    @Transactional
    @Query("DELETE FROM DoWorkoutExerciseSet s WHERE s.workoutId = :workoutId")
    void deleteByWorkoutId(Long workoutId);

    @Modifying
    @Transactional
    @Query("UPDATE DoWorkoutExerciseSet s SET s.doWorkoutPerformanceMetricsId = :workoutPerformanceMetricsId, s.workoutId = :workoutId, s.exerciseId = :exerciseId, s.templateSetId = :templateSetId, s.reps = :reps, s.weight = :weight, s.isDone = :isDone, s.time = :time, s.date = :date WHERE s.instanceId = :instanceId")
    void updateSet(UUID instanceId,
                   Long doWorkoutPerformanceMetricsId,
                   Long workoutId,
                   Long exerciseId,
                   UUID templateSetId,
                   Integer reps,
                   Float weight,
                   Boolean isDone,
                   ExerciseTime time,
                   Date date
    );
}

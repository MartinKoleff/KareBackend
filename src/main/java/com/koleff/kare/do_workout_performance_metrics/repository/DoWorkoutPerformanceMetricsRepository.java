package com.koleff.kare.do_workout_performance_metrics.repository;


import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface DoWorkoutPerformanceMetricsRepository extends JpaRepository<DoWorkoutPerformanceMetrics, Long> {

    @Query("SELECT d FROM DoWorkoutPerformanceMetrics d LEFT JOIN FETCH d.doWorkoutExerciseSets WHERE d.id = :id")
    DoWorkoutPerformanceMetrics findWorkoutPerformanceMetricsById(Long id);

    @Query("SELECT d FROM DoWorkoutPerformanceMetrics d LEFT JOIN FETCH d.doWorkoutExerciseSets WHERE d.id = :id AND d.date BETWEEN :start AND :end")
    List<DoWorkoutPerformanceMetrics> findAllWorkoutPerformanceMetricsById(Long id, Date start, Date end);

    @Query("SELECT d FROM DoWorkoutPerformanceMetrics d LEFT JOIN FETCH d.doWorkoutExerciseSets WHERE d.workoutId = :workoutId AND d.date BETWEEN :start AND :end")
    List<DoWorkoutPerformanceMetrics> findAllWorkoutPerformanceMetricsByWorkoutId(Long workoutId, Date start, Date end);

    @Query("SELECT d FROM DoWorkoutPerformanceMetrics d LEFT JOIN FETCH d.doWorkoutExerciseSets")
    List<DoWorkoutPerformanceMetrics> findAllWorkoutPerformanceMetrics();

    @Query("SELECT d FROM DoWorkoutPerformanceMetrics d LEFT JOIN FETCH d.doWorkoutExerciseSets WHERE d.date BETWEEN :start AND :end")
    List<DoWorkoutPerformanceMetrics> findAllWorkoutPerformanceMetrics(Date start, Date end);

    @Query("SELECT d FROM DoWorkoutPerformanceMetrics d LEFT JOIN FETCH d.doWorkoutExerciseSets WHERE d.workoutId = :workoutId ORDER BY d.date DESC")
    List<DoWorkoutPerformanceMetrics> findAllWorkoutPerformanceMetricsByWorkoutId(Long workoutId);

    @Query("DELETE FROM DoWorkoutPerformanceMetrics s WHERE s.workoutId = :workoutId")
    void deleteByWorkoutId(Long workoutId);

//    @Modifying
//    @Transactional
//    @Query("UPDATE DoWorkoutPerformanceMetrics d SET d.workoutId = :workoutId, d.date = :date WHERE d.id = :id")
//    void updateWorkoutPerformanceMetrics(
//            Long id,
//            Long workoutId,
//            Date date
//    );
}

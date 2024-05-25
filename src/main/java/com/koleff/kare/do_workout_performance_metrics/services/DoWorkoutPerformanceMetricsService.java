package com.koleff.kare.do_workout_performance_metrics.services;

import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;

import java.util.Date;
import java.util.List;

public interface DoWorkoutPerformanceMetricsService {

    DoWorkoutPerformanceMetricsDto saveDoWorkoutPerformanceMetrics(DoWorkoutPerformanceMetricsDto performanceMetricsDto);
    List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetrics(Long id, Date start, Date end);
    DoWorkoutPerformanceMetricsDto getDoWorkoutPerformanceMetricsById(Long id);
    List<DoWorkoutPerformanceMetricsDto> getDoWorkoutPerformanceMetricsByWorkoutId(Long workoutId);
    List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetricsByWorkoutId(Long workoutId, Date start, Date end);
    List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetrics();
    List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetrics(Date start, Date end);
    void deleteDoWorkoutPerformanceMetrics(Long id);
    DoWorkoutPerformanceMetricsDto updateDoWorkoutPerformanceMetrics(DoWorkoutPerformanceMetricsDto performanceMetricsDto);
}

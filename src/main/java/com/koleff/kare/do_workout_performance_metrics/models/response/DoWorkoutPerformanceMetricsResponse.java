package com.koleff.kare.do_workout_performance_metrics.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;
import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;

public class DoWorkoutPerformanceMetricsResponse extends BaseResponse {
    public DoWorkoutPerformanceMetricsDto doWorkoutPerformanceMetrics;

    public DoWorkoutPerformanceMetricsResponse(DoWorkoutPerformanceMetricsDto doWorkoutPerformanceMetrics, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.doWorkoutPerformanceMetrics = doWorkoutPerformanceMetrics;
    }
}

package com.koleff.kare.do_workout_performance_metrics.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;

public class DoWorkoutPerformanceMetricsResponse extends BaseResponse {
    public DoWorkoutPerformanceMetricsDto doWorkoutPerformanceMetrics;

    public DoWorkoutPerformanceMetricsResponse(DoWorkoutPerformanceMetricsDto doWorkoutPerformanceMetrics, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.doWorkoutPerformanceMetrics = doWorkoutPerformanceMetrics;
    }
}

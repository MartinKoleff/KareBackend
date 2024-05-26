package com.koleff.kare.do_workout_performance_metrics.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;

import java.util.List;

public class DoWorkoutPerformanceMetricsListResponse extends BaseResponse {
    public List<DoWorkoutPerformanceMetricsDto> doWorkoutPerformanceMetricsList;

    public DoWorkoutPerformanceMetricsListResponse(List<DoWorkoutPerformanceMetricsDto> doWorkoutPerformanceMetricsList, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.doWorkoutPerformanceMetricsList = doWorkoutPerformanceMetricsList;
    }
}

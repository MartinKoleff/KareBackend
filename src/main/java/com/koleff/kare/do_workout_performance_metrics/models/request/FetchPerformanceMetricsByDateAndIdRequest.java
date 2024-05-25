package com.koleff.kare.do_workout_performance_metrics.models.request;

import java.util.Date;

public record FetchPerformanceMetricsByDateAndIdRequest(Long performanceMetricsId, Date start, Date end){

}

package com.koleff.kare.do_workout_performance_metrics.models.request;

import java.util.Date;

public record FetchPerformanceMetricsByDateRequest(Date start, Date end){

}

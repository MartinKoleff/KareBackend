package com.koleff.kare.do_workout_performance_metrics.models.request;

import java.util.Date;

public record FetchPerformanceMetricsByDateAndWorkoutIdRequest(Long workoutId, Date start, Date end){

}

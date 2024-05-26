package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class DoWorkoutPerformanceMetricsNotFoundException extends KareException {
    public DoWorkoutPerformanceMetricsNotFoundException() {
        super(KareError.DO_WORKOUT_PERFORMANCE_METRICS_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public DoWorkoutPerformanceMetricsNotFoundException(Throwable cause) {
        super(KareError.DO_WORKOUT_PERFORMANCE_METRICS_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
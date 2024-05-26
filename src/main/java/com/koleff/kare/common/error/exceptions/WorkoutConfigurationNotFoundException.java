package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class WorkoutConfigurationNotFoundException extends KareException {
    public WorkoutConfigurationNotFoundException() {
        super(KareError.WORKOUT_CONFIGURATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public WorkoutConfigurationNotFoundException(Throwable cause) {
        super(KareError.WORKOUT_CONFIGURATION_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
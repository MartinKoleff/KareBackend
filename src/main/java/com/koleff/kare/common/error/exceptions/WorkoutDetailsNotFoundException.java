package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class WorkoutDetailsNotFoundException extends KareException {
    public WorkoutDetailsNotFoundException() {
        super(KareError.WORKOUT_DETAILS_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public WorkoutDetailsNotFoundException(Throwable cause) {
        super(KareError.WORKOUT_DETAILS_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
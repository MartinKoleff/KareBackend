package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class WorkoutNotFoundException extends KareException {
    public WorkoutNotFoundException() {
        super(KareError.WORKOUT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public WorkoutNotFoundException(Throwable cause) {
        super(KareError.WORKOUT_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class InvalidWorkoutException extends KareException {
    public InvalidWorkoutException() {
        super(KareError.INVALID_WORKOUT, HttpStatus.BAD_REQUEST);
    }

    public InvalidWorkoutException(Throwable cause) {
        super(KareError.INVALID_WORKOUT, HttpStatus.BAD_REQUEST, cause);
    }
}
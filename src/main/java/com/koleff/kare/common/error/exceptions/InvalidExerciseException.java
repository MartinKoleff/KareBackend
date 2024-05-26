package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class InvalidExerciseException extends KareException {
    public InvalidExerciseException() {
        super(KareError.INVALID_EXERCISE, HttpStatus.BAD_REQUEST);
    }

    public InvalidExerciseException(Throwable cause) {
        super(KareError.INVALID_EXERCISE, HttpStatus.BAD_REQUEST, cause);
    }
}
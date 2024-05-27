package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class InvalidExerciseDetailsException extends KareException {
    public InvalidExerciseDetailsException() {
        super(KareError.INVALID_EXERCISE, HttpStatus.BAD_REQUEST);
    }

    public InvalidExerciseDetailsException(Throwable cause) {
        super(KareError.INVALID_EXERCISE_DETAILS, HttpStatus.BAD_REQUEST, cause);
    }
}
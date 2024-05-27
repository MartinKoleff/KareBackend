package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class ExerciseDetailsNotFoundException extends KareException {
    public ExerciseDetailsNotFoundException() {
        super(KareError.EXERCISE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ExerciseDetailsNotFoundException(Throwable cause) {
        super(KareError.EXERCISE_DETAILS_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
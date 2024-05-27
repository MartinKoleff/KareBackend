package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class ExerciseNotFoundException extends KareException {
    public ExerciseNotFoundException() {
        super(KareError.EXERCISE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ExerciseNotFoundException(Throwable cause) {
        super(KareError.EXERCISE_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
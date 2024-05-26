package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class ExerciseSetNotFoundException extends KareException {
    public ExerciseSetNotFoundException() {
        super(KareError.EXERCISE_SET_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ExerciseSetNotFoundException(Throwable cause) {
        super(KareError.EXERCISE_SET_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}
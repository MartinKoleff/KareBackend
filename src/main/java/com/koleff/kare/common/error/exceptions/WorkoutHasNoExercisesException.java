package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class WorkoutHasNoExercisesException extends KareException {
    public WorkoutHasNoExercisesException() {
        super(KareError.WORKOUT_HAS_NO_EXERCISES, HttpStatus.BAD_REQUEST);
    }

    public WorkoutHasNoExercisesException(Throwable cause) {
        super(KareError.WORKOUT_HAS_NO_EXERCISES, HttpStatus.BAD_REQUEST, cause);
    }
}
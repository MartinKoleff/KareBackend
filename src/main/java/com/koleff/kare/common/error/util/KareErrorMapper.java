package com.koleff.kare.common.error.util;

import com.koleff.kare.common.error.exceptions.*;
import com.koleff.kare.common.error.kare_error.KareError;

public class KareErrorMapper {

    public static KareError fromException(RuntimeException e) {
        if (e instanceof InvalidCredentialsException) {
            return KareError.INVALID_CREDENTIALS;
        } else if (e instanceof InvalidExerciseException) {
            return KareError.INVALID_EXERCISE;
        } else if (e instanceof InvalidWorkoutException) {
            return KareError.INVALID_WORKOUT;
        } else if (e instanceof WorkoutHasNoExercisesException) {
            return KareError.WORKOUT_HAS_NO_EXERCISES;
        } else if (e instanceof UserNotFoundException) {
            return KareError.USER_NOT_FOUND;
        } else if (e instanceof ExerciseNotFoundException) {
            return KareError.EXERCISE_NOT_FOUND;
        } else if (e instanceof ExerciseSetNotFoundException) {
            return KareError.EXERCISE_SET_NOT_FOUND;
        } else if (e instanceof WorkoutNotFoundException) {
            return KareError.WORKOUT_NOT_FOUND;
        } else if (e instanceof WorkoutDetailsNotFoundException) {
            return KareError.WORKOUT_DETAILS_NOT_FOUND;
        } else if (e instanceof DoWorkoutPerformanceMetricsNotFoundException) {
            return KareError.DO_WORKOUT_PERFORMANCE_METRICS_NOT_FOUND;
        } else if (e instanceof WorkoutConfigurationNotFoundException) {
            return KareError.WORKOUT_CONFIGURATION_NOT_FOUND;
        } else if (e instanceof TokenExpiredException) {
            return KareError.TOKEN_EXPIRED;
        } else if (e instanceof InvalidTokenException) {
            return KareError.INVALID_TOKEN;
        } else {
            return KareError.GENERIC;
        }
    }
}

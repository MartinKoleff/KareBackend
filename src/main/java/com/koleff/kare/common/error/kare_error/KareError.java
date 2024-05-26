package com.koleff.kare.common.error.kare_error;

public enum KareError {
    OK("success", ErrorType.SUCCESS),
    GENERIC("error_generic", ErrorType.INTERNAL),
    NETWORK("error_network", ErrorType.SERVER),
    INVALID_CREDENTIALS("error_invalid_credentials", ErrorType.INTERNAL),
    USER_NOT_FOUND("error_user_not_found", ErrorType.INTERNAL),
    INVALID_EXERCISE("error_invalid_exercise", ErrorType.INTERNAL),
    INVALID_WORKOUT("error_invalid_workout", ErrorType.INTERNAL),
    WORKOUT_HAS_NO_EXERCISES("error_workout_has_no_exercises", ErrorType.INTERNAL),
    EXERCISE_NOT_FOUND("error_exercise_not_found", ErrorType.INTERNAL),
    EXERCISE_SET_NOT_FOUND("error_exercise_set_not_found", ErrorType.INTERNAL),
    WORKOUT_NOT_FOUND("error_workout_not_found", ErrorType.INTERNAL),
    WORKOUT_DETAILS_NOT_FOUND("error_workout_details_not_found", ErrorType.INTERNAL),
    DO_WORKOUT_PERFORMANCE_METRICS_NOT_FOUND("error_do_workout_performance_metrics_not_found", ErrorType.INTERNAL),
    WORKOUT_CONFIGURATION_NOT_FOUND("error_workout_configuration_not_found", ErrorType.INTERNAL),
    TOKEN_EXPIRED("error_token_expired", ErrorType.INTERNAL),
    INVALID_TOKEN("error_invalid_token", ErrorType.INTERNAL);

    private final String errorCode;
    private final ErrorType errorType;

    KareError(String errorCode, ErrorType errorType) {
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public static KareError fromErrorCode(String errorCode) {
        for (KareError error : values()) {
            if (error.getErrorCode().equals(errorCode)) {
                return error;
            }
        }
        return GENERIC;
    }
}


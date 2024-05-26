package com.koleff.kare.common.error.handler;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.exceptions.*;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.common.error.util.KareErrorMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class KareExceptionHandler {
    private final static Logger logger = LogManager.getLogger(KareExceptionHandler.class);

    @ExceptionHandler(value = {
            DoWorkoutPerformanceMetricsNotFoundException.class,
            ExerciseNotFoundException.class,
            ExerciseSetNotFoundException.class,
            UserNotFoundException.class,
            WorkoutConfigurationNotFoundException.class,
            WorkoutDetailsNotFoundException.class,
            WorkoutNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException e) {

        //Returned to the client
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        logger.error("Error thrown with status code: " + httpStatus.value());

        return new ResponseEntity<>(
                e,
                httpStatus
        );
    }


    @ExceptionHandler(value = {
            InvalidCredentialsException.class,
            InvalidExerciseException.class,
            InvalidWorkoutException.class,
            WorkoutHasNoExercisesException.class
    })
    public ResponseEntity<Object> handleInvalidDataException(RuntimeException e) {

        //Returned to the client
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.error("Error thrown with status code: " + httpStatus.value());

        KareError kareError = KareErrorMapper.fromException(e);
        logger.error("Error: " + kareError);

        return new ResponseEntity<>(
                new BaseResponse(
                        false,
                        kareError
                ),
                httpStatus
        );
    }

    @ExceptionHandler(value = {
            InvalidTokenException.class
    })
    public ResponseEntity<Object> handleInvalidTokenException(RuntimeException e) {

        //Returned to the client
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        logger.error("Error thrown with status code: " + httpStatus.value());

        KareError kareError = KareErrorMapper.fromException(e);
        logger.error("Error: " + kareError);

        return new ResponseEntity<>(
                new BaseResponse(
                        false,
                        kareError
                ),
                httpStatus
        );
    }

    @ExceptionHandler(value = {
            TokenExpiredException.class
    })
    public ResponseEntity<Object> handleTokenExpiredException(RuntimeException e) {

        //Returned to the client
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        logger.error("Error thrown with status code: " + httpStatus.value());

        KareError kareError = KareErrorMapper.fromException(e);
        logger.error("Error: " + kareError);

        return new ResponseEntity<>(
                new BaseResponse(
                        false,
                        kareError
                ),
                httpStatus
        );
    }

    @ExceptionHandler(value = {
            NetworkException.class
    })
    public ResponseEntity<Object> handleNetworkException(RuntimeException e) {

        //Returned to the client
        HttpStatus httpStatus = HttpStatus.REQUEST_TIMEOUT;
        logger.error("Error thrown with status code: " + httpStatus.value());

        KareError kareError = KareErrorMapper.fromException(e);
        logger.error("Error: " + kareError);

        return new ResponseEntity<>(
                new BaseResponse(
                        false,
                        kareError
                ),
                httpStatus
        );
    }

    @ExceptionHandler(value = {
            GenericException.class
    })
    public ResponseEntity<Object> handleGenericException(RuntimeException e) {

        //Returned to the client
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.error("Error thrown with status code: " + httpStatus.value());

        KareError kareError = KareErrorMapper.fromException(e);
        logger.error("Error: " + kareError);

        return new ResponseEntity<>(
                new BaseResponse(
                        false,
                        kareError
                ),
                httpStatus
        );
    }
}

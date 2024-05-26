package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends KareException {
    public InvalidTokenException() {
        super(KareError.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    public InvalidTokenException(Throwable cause) {
        super(KareError.INVALID_TOKEN, HttpStatus.UNAUTHORIZED, cause);
    }
}
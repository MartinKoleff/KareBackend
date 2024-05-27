package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends KareException {
    public InvalidCredentialsException() {
        super(KareError.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
    }

    public InvalidCredentialsException(Throwable cause) {
        super(KareError.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED, cause);
    }
}

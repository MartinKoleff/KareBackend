package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends KareException {
    public UserNotFoundException() {
        super(KareError.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(Throwable cause) {
        super(KareError.USER_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}

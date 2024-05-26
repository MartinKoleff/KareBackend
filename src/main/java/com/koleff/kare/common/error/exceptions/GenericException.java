package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class GenericException extends KareException {
    public GenericException() {
        super(KareError.GENERIC, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public GenericException(Throwable cause) {
        super(KareError.GENERIC, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}

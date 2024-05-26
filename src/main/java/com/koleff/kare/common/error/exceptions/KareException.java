package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.common.error.util.ExceptionUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;


public @Data class KareException extends RuntimeException  {

    private final KareError kareError;
    private final HttpStatus httpStatus;
    private final Date timestamp;

    public KareException(KareError kareError, HttpStatus httpStatus, Throwable cause) {
        super(kareError.getErrorCode(), cause);
        this.kareError = kareError;
        this.httpStatus = httpStatus;
        this.timestamp = new Date();
    }

    public KareException(KareError kareError, HttpStatus httpStatus) {
        super(kareError.getErrorCode());
        this.kareError = kareError;
        this.httpStatus = httpStatus;
        this.timestamp = new Date();
    }
}


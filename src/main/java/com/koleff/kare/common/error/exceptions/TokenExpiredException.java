package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends KareException {
    public TokenExpiredException() {
        super(KareError.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
    }

    public TokenExpiredException(Throwable cause) {
        super(KareError.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED, cause);
    }
}
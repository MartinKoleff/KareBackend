package com.koleff.kare.common.error.exceptions;

import com.koleff.kare.common.error.kare_error.KareError;
import org.springframework.http.HttpStatus;

public class NetworkException extends KareException {
    public NetworkException() {
        super(KareError.NETWORK, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public NetworkException(Throwable cause) {
        super(KareError.NETWORK, HttpStatus.SERVICE_UNAVAILABLE, cause);
    }
}

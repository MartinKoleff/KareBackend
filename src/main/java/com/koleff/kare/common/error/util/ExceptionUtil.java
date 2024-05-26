package com.koleff.kare.common.error.util;

import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.common.error.exceptions.KareException;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionUtil {

    public static KareException toApiException(KareError kareError, Throwable throwable) {
        HttpStatus status = switch (kareError.getErrorType()) {
            case SUCCESS -> HttpStatus.OK;
            case SERVER -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.BAD_REQUEST;
        };

        return new KareException(
                kareError,
                status,
                throwable
        );
    }
}

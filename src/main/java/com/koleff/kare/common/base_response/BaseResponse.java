package com.koleff.kare.common.base_response;

import com.koleff.kare.common.error.kare_error.KareError;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class BaseResponse {
    private boolean isSuccessful;
    private KareError error;
}

package com.koleff.kare.common.base_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public @Data class BaseResponse {
    private boolean isSuccessful;
    private KareError error;
}

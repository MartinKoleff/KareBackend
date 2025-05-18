package com.koleff.kare.statistics.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;

public class TotalRepsResponse extends BaseResponse {
    private Integer totalReps;

    public TotalRepsResponse(Integer totalReps, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.totalReps = totalReps;
    }
}
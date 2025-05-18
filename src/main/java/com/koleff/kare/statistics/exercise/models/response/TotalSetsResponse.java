package com.koleff.kare.statistics.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;

public class TotalSetsResponse extends BaseResponse {
    private Integer totalSets;

    public TotalSetsResponse(Integer totalSets, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.totalSets = totalSets;
    }
}
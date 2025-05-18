package com.koleff.kare.statistics.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;

public class TotalWeightLiftedResponse extends BaseResponse {
    private Float totalWeight;

    public TotalWeightLiftedResponse(Float totalWeight, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.totalWeight = totalWeight;
    }
}
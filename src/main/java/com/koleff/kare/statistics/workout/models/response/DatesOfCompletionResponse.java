package com.koleff.kare.statistics.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;

import java.util.Date;
import java.util.List;

public class DatesOfCompletionResponse extends BaseResponse {
    private List<Date> datesOfCompletion;

    public DatesOfCompletionResponse(List<Date> datesOfCompletion, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.datesOfCompletion = datesOfCompletion;
    }
}
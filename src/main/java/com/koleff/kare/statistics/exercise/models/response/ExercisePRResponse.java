package com.koleff.kare.statistics.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.exercise.models.dto.ExerciseDto;

public class ExercisePRResponse extends BaseResponse {
    private Float pr;

    public ExercisePRResponse(Float pr, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.pr = pr;
    }
}
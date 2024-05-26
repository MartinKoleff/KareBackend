package com.koleff.kare.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.exercise.models.dto.ExerciseDto;

public class ExerciseResponse extends BaseResponse {
    private ExerciseDto exercise;

    public ExerciseResponse(ExerciseDto exercise, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.exercise = exercise;
    }
}
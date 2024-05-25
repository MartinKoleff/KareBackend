package com.koleff.kare.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;

public class ExerciseDetailsResponse extends BaseResponse {
    private ExerciseDetailsDto exerciseDetails;

    public ExerciseDetailsResponse(ExerciseDetailsDto exerciseDetails, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.exerciseDetails = exerciseDetails;
    }
}
package com.koleff.kare.exercise.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.exercise.models.dto.ExerciseDto;

import java.util.List;

public class ExerciseListResponse extends BaseResponse {
    private List<ExerciseDto> exercises;

    public ExerciseListResponse(List<ExerciseDto> exercises, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.exercises = exercises;
    }
}
package com.koleff.kare.statistics.general.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.exercise.models.entity.MuscleGroup;

public class MuscleGroupResponse extends BaseResponse {
    private MuscleGroup muscleGroup;

    public MuscleGroupResponse(MuscleGroup muscleGroup, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.muscleGroup = muscleGroup;
    }
}
package com.koleff.kare.statistics.general.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.statistics.general.models.dto.MuscleGroupMaxWeight;

public class StrongestMuscleGroupResponse extends BaseResponse {
    private MuscleGroupMaxWeight data;

    public StrongestMuscleGroupResponse(MuscleGroupMaxWeight data, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.data = data;
    }
}
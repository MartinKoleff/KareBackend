package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDto;

public class WorkoutResponse extends BaseResponse {
    public WorkoutDto workout;

    public WorkoutResponse(WorkoutDto workout, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workout = workout;
    }
}

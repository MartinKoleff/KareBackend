package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import lombok.AllArgsConstructor;

public class WorkoutResponse extends BaseResponse {
    public WorkoutDto workout;

    public WorkoutResponse(WorkoutDto workout, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workout = workout;
    }
}

package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDto;

import java.util.List;

public class WorkoutListResponse extends BaseResponse {
    public List<WorkoutDto> workouts;

    public WorkoutListResponse(List<WorkoutDto> workouts, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workouts = workouts;
    }
}

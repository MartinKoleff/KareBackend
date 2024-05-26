package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;

public class WorkoutDetailsResponse extends BaseResponse {
    private WorkoutDetailsDto workoutDetails;

    public WorkoutDetailsResponse(WorkoutDetailsDto workoutDetails, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workoutDetails = workoutDetails;
    }
}

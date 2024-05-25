package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import lombok.AllArgsConstructor;

public class WorkoutDetailsResponse extends BaseResponse {
    private WorkoutDetailsDto workoutDetails;

    public WorkoutDetailsResponse(WorkoutDetailsDto workoutDetails, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workoutDetails = workoutDetails;
    }
}

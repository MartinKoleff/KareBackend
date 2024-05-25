package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import lombok.AllArgsConstructor;

import java.util.List;

public class WorkoutDetailsListResponse extends BaseResponse {
    private List<WorkoutDetailsDto> workoutDetailsList;

    public WorkoutDetailsListResponse(List<WorkoutDetailsDto> workoutDetailsList, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workoutDetailsList = workoutDetailsList;
    }
}

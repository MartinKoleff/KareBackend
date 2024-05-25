package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class WorkoutListResponse extends BaseResponse {
    public List<WorkoutDto> workouts;

    public WorkoutListResponse(List<WorkoutDto> workouts, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workouts = workouts;
    }
}

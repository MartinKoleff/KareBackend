package com.koleff.kare.workout.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.base_response.KareError;
import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import lombok.AllArgsConstructor;

public class WorkoutConfigurationResponse extends BaseResponse {
    public WorkoutConfigurationDto workoutConfiguration;

    public WorkoutConfigurationResponse(WorkoutConfigurationDto workoutConfiguration, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.workoutConfiguration = workoutConfiguration;
    }
}

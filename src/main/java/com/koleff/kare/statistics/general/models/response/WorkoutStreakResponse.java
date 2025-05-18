package com.koleff.kare.statistics.general.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;

public class WorkoutStreakResponse extends BaseResponse {
    private Integer streak;

    public WorkoutStreakResponse(Integer streak, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.streak = streak;
    }
}
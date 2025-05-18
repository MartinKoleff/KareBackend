package com.koleff.kare.statistics.general.models.response;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.common.error.kare_error.KareError;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;

public class TotalTimesCompletedResponse extends BaseResponse {
    private WorkoutTotalTimesCompleted data;

    public TotalTimesCompletedResponse(WorkoutTotalTimesCompleted data, boolean isSuccessful, KareError error) {
        super(isSuccessful, error);
        this.data = data;
    }
}
package com.koleff.kare.statistics.exercise.controller;

import com.koleff.kare.statistics.exercise.models.request.ExerciseStatisticsRequest;
import com.koleff.kare.statistics.exercise.models.response.ExercisePRResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalRepsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalSetsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalWeightLiftedResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface ExerciseStatisticsController {
    ExercisePRResponse getBestPR(@RequestBody ExerciseStatisticsRequest request);
    ExercisePRResponse get1RepMax(@RequestBody ExerciseStatisticsRequest request);
    TotalRepsResponse getTotalRepsPerformed(@RequestBody ExerciseStatisticsRequest request);
    TotalSetsResponse getTotalSetsPerformed(@RequestBody ExerciseStatisticsRequest request);
    TotalWeightLiftedResponse getTotalWeightLifted(@RequestBody ExerciseStatisticsRequest request);
}

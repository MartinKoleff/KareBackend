package com.koleff.kare.statistics.workout.controller;

import com.koleff.kare.statistics.exercise.models.request.ExerciseStatisticsRequest;
import com.koleff.kare.statistics.exercise.models.response.ExercisePRResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalRepsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalSetsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalWeightLiftedResponse;
import com.koleff.kare.statistics.general.models.response.TotalTimesCompletedResponse;
import com.koleff.kare.statistics.workout.models.response.DatesOfCompletionResponse;
import com.koleff.kare.workout.models.request.FetchWorkoutByWorkoutIdRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface WorkoutStatisticsController {

    TotalTimesCompletedResponse getTotalTimesCompleted(@RequestBody FetchWorkoutByWorkoutIdRequest request);

    TotalWeightLiftedResponse getTotalWeightLifted(@RequestBody FetchWorkoutByWorkoutIdRequest request);

    DatesOfCompletionResponse getDatesOfCompletionForWorkout(@RequestBody FetchWorkoutByWorkoutIdRequest request);

    TotalRepsResponse getTotalRepsPerformed(@RequestBody FetchWorkoutByWorkoutIdRequest request);

    TotalSetsResponse getTotalSetsPerformed(@RequestBody FetchWorkoutByWorkoutIdRequest request);
}

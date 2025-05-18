package com.koleff.kare.statistics.workout.controller;

import com.koleff.kare.statistics.exercise.controller.ExerciseStatisticsController;
import com.koleff.kare.statistics.exercise.models.request.ExerciseStatisticsRequest;
import com.koleff.kare.statistics.exercise.models.response.ExercisePRResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalRepsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalSetsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalWeightLiftedResponse;
import com.koleff.kare.statistics.exercise.service.ExerciseStatisticsService;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;
import com.koleff.kare.statistics.general.models.response.TotalTimesCompletedResponse;
import com.koleff.kare.statistics.workout.models.response.DatesOfCompletionResponse;
import com.koleff.kare.statistics.workout.service.WorkoutStatisticsService;
import com.koleff.kare.workout.models.request.FetchWorkoutByWorkoutIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1/statistics/workout")
@CrossOrigin("*")
public class WorkoutStatisticsControllerImpl implements WorkoutStatisticsController {

    private final WorkoutStatisticsService workoutStatisticsService;

    @Autowired
    public WorkoutStatisticsControllerImpl(WorkoutStatisticsService workoutStatisticsService) {
        this.workoutStatisticsService = workoutStatisticsService;
    }


    @GetMapping("gettotaltimescompleted")
    @Override
    public TotalTimesCompletedResponse getTotalTimesCompleted(FetchWorkoutByWorkoutIdRequest request) {
        WorkoutTotalTimesCompleted totalTimesCompleted = workoutStatisticsService.getTotalTimesCompleted(request.workoutId());

        return new TotalTimesCompletedResponse(
                totalTimesCompleted,
                true,
                null
        );
    }

    @GetMapping("gettotalweightlifted")
    @Override
    public TotalWeightLiftedResponse getTotalWeightLifted(FetchWorkoutByWorkoutIdRequest request) {
        Float totalWeight = workoutStatisticsService.getTotalWeightLifted(
                request.workoutId()
        );

        return new TotalWeightLiftedResponse(
                totalWeight,
                true,
                null
        );
    }

    @GetMapping("getdatesofcompletion")
    @Override
    public DatesOfCompletionResponse getDatesOfCompletionForWorkout(FetchWorkoutByWorkoutIdRequest request) {
        List<Date> datesOfCompletion = workoutStatisticsService.getDatesOfCompletionForWorkout(
                request.workoutId()
        );

        return new DatesOfCompletionResponse(
                datesOfCompletion,
                true,
                null
        );
    }

    @GetMapping("gettotalrepsperformed")
    @Override
    public TotalRepsResponse getTotalRepsPerformed(FetchWorkoutByWorkoutIdRequest request) {
        Integer totalReps = workoutStatisticsService.getTotalRepsPerformed(
                request.workoutId()
        );

        return new TotalRepsResponse(
                totalReps,
                true,
                null
        );
    }

    @GetMapping("gettotalsetsperformed")
    @Override
    public TotalSetsResponse getTotalSetsPerformed(FetchWorkoutByWorkoutIdRequest request) {
        Integer totalSets = workoutStatisticsService.getTotalSetsPerformed(
                request.workoutId()
        );

        return new TotalSetsResponse(
                totalSets,
                true,
                null
        );
    }
}

package com.koleff.kare.statistics.general.controller;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.exercise.models.request.FetchExercisesByMuscleGroupRequest;
import com.koleff.kare.exercise.models.response.ExerciseResponse;
import com.koleff.kare.statistics.exercise.controller.ExerciseStatisticsController;
import com.koleff.kare.statistics.exercise.models.request.ExerciseStatisticsRequest;
import com.koleff.kare.statistics.exercise.models.response.ExercisePRResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalRepsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalSetsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalWeightLiftedResponse;
import com.koleff.kare.statistics.exercise.service.ExerciseStatisticsService;
import com.koleff.kare.statistics.general.models.dto.MuscleGroupMaxWeight;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;
import com.koleff.kare.statistics.general.models.response.MuscleGroupResponse;
import com.koleff.kare.statistics.general.models.response.StrongestMuscleGroupResponse;
import com.koleff.kare.statistics.general.models.response.TotalTimesCompletedResponse;
import com.koleff.kare.statistics.general.models.response.WorkoutStreakResponse;
import com.koleff.kare.statistics.general.service.GeneralStatisticsService;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.response.WorkoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/statistics/general")
@CrossOrigin("*")
public class GeneralStatisticsControllerImpl implements GeneralStatisticsController {

    private final GeneralStatisticsService generalStatisticsService;

    @Autowired
    public GeneralStatisticsControllerImpl(GeneralStatisticsService generalStatisticsService) {
        this.generalStatisticsService = generalStatisticsService;
    }

    @GetMapping("getworkoutscompleted")
    @Override
    public TotalTimesCompletedResponse getWorkoutsCompleted() {
        WorkoutTotalTimesCompleted totalTimesCompleted = generalStatisticsService.getWorkoutsCompleted();

        return new TotalTimesCompletedResponse(
                totalTimesCompleted,
                true,
                null
        );
    }

    @GetMapping("getdistinctworkoutscompleted")
    @Override
    public TotalTimesCompletedResponse getDistinctWorkoutsCompleted() {
        WorkoutTotalTimesCompleted totalTimesCompleted = generalStatisticsService.getDistinctWorkoutsCompleted();

        return new TotalTimesCompletedResponse(
                totalTimesCompleted,
                true,
                null
        );
    }

    @GetMapping("getworkoutstreak")
    @Override
    public WorkoutStreakResponse getWorkoutStreak() {
        Integer streak = generalStatisticsService.getWorkoutStreak();

        return new WorkoutStreakResponse(
                streak,
                true,
                null
        );
    }

    @GetMapping("getmostfrequentworkout")
    @Override
    public WorkoutResponse getMostFrequentWorkout() {
        WorkoutDto mostFrequentWorkout = generalStatisticsService.getMostFrequentWorkout();

        return new WorkoutResponse(
                mostFrequentWorkout,
                true,
                null
        );
    }

    @GetMapping("getmosttrainedmusclegroup")
    @Override
    public MuscleGroupResponse getMostTrainedMuscleGroup() {
        MuscleGroup mostTrainedMuscleGroup = generalStatisticsService.getMostTrainedMuscleGroup();

        return new MuscleGroupResponse(
                mostTrainedMuscleGroup,
                true,
                null
        );
    }

    @GetMapping("getmosttrainedexercise")
    @Override
    public ExerciseResponse getMostTrainedExercise() {
        ExerciseDto mostTrainedExercise = generalStatisticsService.getMostTrainedExercise();

        return new ExerciseResponse(
                mostTrainedExercise,
                true,
                null
        );
    }

    @GetMapping("gettotalweightlifted")
    @Override
    public TotalWeightLiftedResponse getTotalWeightLifted() {
        Float totalWeightLifted = generalStatisticsService.getTotalWeightLifted();

        return new TotalWeightLiftedResponse(
                totalWeightLifted,
                true,
                null
        );
    }

    @GetMapping("getstrongestmusclegroup")
    @Override
    public StrongestMuscleGroupResponse getStrongestMuscleGroup() {
        MuscleGroupMaxWeight strongestMuscleGroup = generalStatisticsService.getStrongestMuscleGroup();

        return new StrongestMuscleGroupResponse(
                strongestMuscleGroup,
                true,
                null
        );
    }

    @GetMapping("getmusclegrouptotalweightlifted")
    @Override
    public StrongestMuscleGroupResponse getMuscleGroupTotalWeightLifted(FetchExercisesByMuscleGroupRequest request) {
        MuscleGroupMaxWeight muscleGroupTotalWeightLifted = generalStatisticsService.getMuscleGroupTotalWeightLifted(request.muscleGroupId());

        return new StrongestMuscleGroupResponse(
                muscleGroupTotalWeightLifted,
                true,
                null
        );
    }
}

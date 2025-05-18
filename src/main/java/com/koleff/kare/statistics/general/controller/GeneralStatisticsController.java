package com.koleff.kare.statistics.general.controller;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.exercise.models.request.FetchExercisesByMuscleGroupRequest;
import com.koleff.kare.exercise.models.response.ExerciseResponse;
import com.koleff.kare.statistics.exercise.models.response.ExercisePRResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalWeightLiftedResponse;
import com.koleff.kare.statistics.general.models.dto.MuscleGroupMaxWeight;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;
import com.koleff.kare.statistics.general.models.response.MuscleGroupResponse;
import com.koleff.kare.statistics.general.models.response.StrongestMuscleGroupResponse;
import com.koleff.kare.statistics.general.models.response.TotalTimesCompletedResponse;
import com.koleff.kare.statistics.general.models.response.WorkoutStreakResponse;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.response.WorkoutResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface GeneralStatisticsController {
    TotalTimesCompletedResponse getWorkoutsCompleted();

    TotalTimesCompletedResponse getDistinctWorkoutsCompleted();

    WorkoutStreakResponse getWorkoutStreak();

    WorkoutResponse getMostFrequentWorkout();

    MuscleGroupResponse getMostTrainedMuscleGroup();

    ExerciseResponse getMostTrainedExercise();

    TotalWeightLiftedResponse getTotalWeightLifted();

    StrongestMuscleGroupResponse getStrongestMuscleGroup();

    StrongestMuscleGroupResponse getMuscleGroupTotalWeightLifted(@RequestBody FetchExercisesByMuscleGroupRequest request);
}
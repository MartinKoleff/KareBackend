package com.koleff.kare.statistics.general.service;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.statistics.general.models.dto.MuscleGroupMaxWeight;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;
import com.koleff.kare.workout.models.dto.WorkoutDto;

public interface GeneralStatisticsService {

    WorkoutTotalTimesCompleted getWorkoutsCompleted();

    WorkoutTotalTimesCompleted getDistinctWorkoutsCompleted();

    Integer getWorkoutStreak();

    WorkoutDto getMostFrequentWorkout();

    MuscleGroup getMostTrainedMuscleGroup();

    ExerciseDto getMostTrainedExercise();

    Float getTotalWeightLifted();

    MuscleGroupMaxWeight getStrongestMuscleGroup();

    MuscleGroupMaxWeight getMuscleGroupTotalWeightLifted(Integer muscleGroupId);
}
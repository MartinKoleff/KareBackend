package com.koleff.kare.workout.service;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface WorkoutCustomService {

    WorkoutDto createNewWorkout();

    WorkoutDto createCustomWorkout(WorkoutDto workout);

    WorkoutDetailsDto createCustomWorkoutDetails(WorkoutDetailsDto workoutDetailsDto);

    WorkoutDetailsDto addExercise(Long workoutId, ExerciseDto exercise);

    WorkoutDetailsDto addMultipleExercises(Long workoutId, List<ExerciseDto> exercises);

    WorkoutDetailsDto submitExercise(Long workoutId, ExerciseDto exercise);

    WorkoutDetailsDto submitMultipleExercises(Long workoutId, List<ExerciseDto> exercises);

    WorkoutDetailsDto deleteExercise(Long workoutId, Long exerciseId);

    WorkoutDetailsDto deleteMultipleExercises(Long workoutId, List<Long> exerciseIds);
}
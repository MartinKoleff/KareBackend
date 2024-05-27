package com.koleff.kare.workout.service;

import com.koleff.kare.workout.models.dto.WorkoutDto;

import java.util.List;

public interface WorkoutService {

    List<WorkoutDto> getWorkoutsOrderedById();
    List<WorkoutDto> getFavoriteWorkouts();
    WorkoutDto getWorkout(Long workoutId);

    WorkoutDto saveWorkout(WorkoutDto workout);

    void saveAllWorkouts(List<WorkoutDto> workouts);

    void deleteWorkout(Long workoutId);

    void updateWorkout(WorkoutDto workout);

    void favoriteWorkout(Long workoutId);

    void unfavoriteWorkout(Long workoutId);
}

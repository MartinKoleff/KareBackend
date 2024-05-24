package com.koleff.kare.workout.models.service;

import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;

import java.util.List;

public interface WorkoutDetailsService {

    List<WorkoutDetailsDto> getWorkoutDetailsOrderedById();

    List<WorkoutDetailsDto> getWorkoutDetailsByIsFavorite();

    WorkoutDetailsDto getWorkoutDetails(Long workoutId);

    WorkoutDetailsDto saveWorkoutDetails(WorkoutDetailsDto workoutDetails);

    void saveAllWorkoutDetails(List<WorkoutDetailsDto> workoutDetailsList);

    void deleteWorkoutDetails(Long workoutId);

    void updateWorkoutDetails(WorkoutDetailsDto workoutDetails);

    void favoriteWorkoutDetailsById(Long workoutId);

    void unfavoriteWorkoutDetailsById(Long workoutId);
}

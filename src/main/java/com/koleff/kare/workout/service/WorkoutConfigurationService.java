package com.koleff.kare.workout.service;

import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;

public interface WorkoutConfigurationService {

    WorkoutConfigurationDto getWorkoutConfiguration(Long workoutId);

    WorkoutConfigurationDto saveWorkoutConfiguration(WorkoutConfigurationDto configuration);

    void updateWorkoutConfiguration(WorkoutConfigurationDto configuration);

    void deleteWorkoutConfiguration(WorkoutConfigurationDto configuration);

    void deleteWorkoutConfiguration(Long workoutId);
}

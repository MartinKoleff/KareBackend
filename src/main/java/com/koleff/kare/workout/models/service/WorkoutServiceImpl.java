package com.koleff.kare.workout.models.service;

import com.koleff.kare.common.Constants;
import com.koleff.kare.workout.mapper.WorkoutMapper;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    @Autowired
    public WorkoutServiceImpl(
            WorkoutRepository workoutRepository,
            WorkoutMapper workoutMapper
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    public List<WorkoutDto> getWorkoutsOrderedById() {
        return workoutRepository.getWorkoutsOrderedById()
                .stream()
                .filter(workout -> !workout.getWorkoutId().equals(Constants.CATALOG_WORKOUT_ID))
                .map(workoutMapper::toDto)
                .toList();
    }

    public List<WorkoutDto> getWorkoutsByIsFavorite() {
        return workoutRepository.getWorkoutsByIsFavorite()
                .stream()
                .filter(workout -> !workout.getWorkoutId().equals(Constants.CATALOG_WORKOUT_ID))
                .map(workoutMapper::toDto)
                .toList();
    }

    public WorkoutDto getWorkout(Long workoutId) {
        return workoutMapper.toDto(
                workoutRepository.getWorkoutByWorkoutId(workoutId)
        );
    }

    public WorkoutDto saveWorkout(WorkoutDto workout) {
        Workout dbEntry = workoutRepository.save(
                workoutMapper.toEntity(workout)
        );

        return workoutMapper.toDto(dbEntry);
    }

    public void saveAllWorkouts(List<WorkoutDto> workouts) {
        List<Workout> dbEntries = workoutRepository.saveAll(
                workouts.stream()
                        .map(workoutMapper::toEntity)
                        .toList()
        );
    }

    public void deleteWorkout(Long workoutId) {
        workoutRepository.deleteWorkout(workoutId);
    }


    //TODO: refactor...
    public void updateWorkout(WorkoutDto workout) {
        saveWorkout(workout);
    }

    public void favoriteWorkoutById(Long workoutId) {
        workoutRepository.favoriteWorkoutById(workoutId);
    }

    public void unfavoriteWorkoutById(Long workoutId) {
        workoutRepository.unfavoriteWorkoutById(workoutId);
    }
}
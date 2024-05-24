package com.koleff.kare.workout.models.service;

import com.koleff.kare.workout.mapper.WorkoutDetailsMapper;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutDetailsService {

    private final WorkoutDetailsRepository workoutDetailsRepository;
    private final WorkoutDetailsMapper workoutDetailsMapper;

    @Autowired
    public WorkoutDetailsService(
            WorkoutDetailsRepository workoutDetailsRepository,
            WorkoutDetailsMapper workoutDetailsMapper
    ) {
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.workoutDetailsMapper = workoutDetailsMapper;
    }

    public List<WorkoutDetailsDto> getWorkoutDetailsOrderedById() {
        return workoutDetailsRepository.getWorkoutDetailsOrderedById()
                .stream()
                .map(workoutDetailsMapper::toDto)
                .toList();
    }

    public List<WorkoutDetailsDto> getWorkoutDetailsByIsFavorite() {
        return workoutDetailsRepository.getWorkoutDetailsByIsFavorite()
                .stream()
                .map(workoutDetailsMapper::toDto)
                .toList();
    }

    public WorkoutDetailsDto getWorkoutDetails(Long workoutId) {
        return workoutDetailsMapper.toDto(
                workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId)
        );
    }

    public WorkoutDetailsDto saveWorkoutDetails(WorkoutDetailsDto workoutDetails) {
        WorkoutDetails dbEntry = workoutDetailsRepository.save(
                workoutDetailsMapper.toEntity(workoutDetails)
        );

        return workoutDetailsMapper.toDto(dbEntry);
    }

    public void saveAllWorkoutDetails(List<WorkoutDetailsDto> workoutDetailsList) {
        List<WorkoutDetails> dbEntries = workoutDetailsRepository.saveAll(
                workoutDetailsList.stream()
                        .map(workoutDetailsMapper::toEntity)
                        .toList()
        );
    }

    public void deleteWorkoutDetails(Long workoutId) {
        workoutDetailsRepository.deleteWorkoutDetails(workoutId);
    }

    //TODO: refactor...
    public void updateWorkoutDetails(WorkoutDetailsDto workoutDetails) {
        saveWorkoutDetails(workoutDetails);
    }

    public void favoriteWorkoutDetailsById(Long workoutId) {
        workoutDetailsRepository.favoriteWorkoutDetailsById(workoutId);
    }

    public void unfavoriteWorkoutDetailsById(Long workoutId) {
        workoutDetailsRepository.unfavoriteWorkoutDetailsById(workoutId);
    }
}

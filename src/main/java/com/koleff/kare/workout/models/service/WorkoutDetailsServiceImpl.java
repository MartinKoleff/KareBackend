package com.koleff.kare.workout.models.service;

import com.koleff.kare.workout.mapper.WorkoutDetailsMapper;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutDetailsServiceImpl implements WorkoutDetailsService {

    private final WorkoutDetailsRepository workoutDetailsRepository;
    private final WorkoutDetailsMapper workoutDetailsMapper;

    @Autowired
    public WorkoutDetailsServiceImpl(
            WorkoutDetailsRepository workoutDetailsRepository,
            WorkoutDetailsMapper workoutDetailsMapper
    ) {
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.workoutDetailsMapper = workoutDetailsMapper;
    }

    @Override
    public List<WorkoutDetailsDto> getWorkoutDetailsOrderedById() {
        return workoutDetailsRepository.getWorkoutDetailsOrderedById()
                .stream()
                .map(workoutDetailsMapper::toDto)
                .toList();
    }

    @Override
    public List<WorkoutDetailsDto> getWorkoutDetailsByIsFavorite() {
        return workoutDetailsRepository.getWorkoutDetailsByIsFavorite()
                .stream()
                .map(workoutDetailsMapper::toDto)
                .toList();
    }

    @Override
    public WorkoutDetailsDto getWorkoutDetails(Long workoutId) {
        return workoutDetailsMapper.toDto(
                workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId)
        );
    }

    @Override
    public WorkoutDetailsDto saveWorkoutDetails(WorkoutDetailsDto workoutDetails) {
        WorkoutDetails dbEntry = workoutDetailsRepository.save(
                workoutDetailsMapper.toEntity(workoutDetails)
        );

        return workoutDetailsMapper.toDto(dbEntry);
    }

    @Override
    public void saveAllWorkoutDetails(List<WorkoutDetailsDto> workoutDetailsList) {
        List<WorkoutDetails> dbEntries = workoutDetailsRepository.saveAll(
                workoutDetailsList.stream()
                        .map(workoutDetailsMapper::toEntity)
                        .toList()
        );
    }

    @Override
    public void deleteWorkoutDetails(Long workoutId) {
        workoutDetailsRepository.deleteWorkoutDetails(workoutId);
    }

    //TODO: refactor...
    @Override
    public void updateWorkoutDetails(WorkoutDetailsDto workoutDetails) {
        saveWorkoutDetails(workoutDetails);
    }

    @Override
    public void favoriteWorkoutDetailsById(Long workoutId) {
        workoutDetailsRepository.favoriteWorkoutDetailsById(workoutId);
    }

    @Override
    public void unfavoriteWorkoutDetailsById(Long workoutId) {
        workoutDetailsRepository.unfavoriteWorkoutDetailsById(workoutId);
    }
}

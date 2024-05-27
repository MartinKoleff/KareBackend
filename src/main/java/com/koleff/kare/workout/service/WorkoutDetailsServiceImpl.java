package com.koleff.kare.workout.service;

import com.koleff.kare.common.error.exceptions.WorkoutDetailsNotFoundException;
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
    public WorkoutDetailsDto getWorkoutDetails(Long workoutId) throws WorkoutDetailsNotFoundException {
        WorkoutDetails workoutDetails =  workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);

        if(workoutDetails == null) throw new WorkoutDetailsNotFoundException();

        return workoutDetailsMapper.toDto(workoutDetails);
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
    public void updateWorkoutDetails(WorkoutDetailsDto workoutDetails) { //throws WorkoutDetailsNotFoundException
        saveWorkoutDetails(workoutDetails);
    }

    @Override
    public void favoriteWorkoutDetailsById(Long workoutId) throws WorkoutDetailsNotFoundException{

        //Validation
        WorkoutDetails workoutDetails =  workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if(workoutDetails == null) throw new WorkoutDetailsNotFoundException();

        workoutDetailsRepository.favoriteWorkoutDetailsById(workoutId);
    }

    @Override
    public void unfavoriteWorkoutDetailsById(Long workoutId) throws WorkoutDetailsNotFoundException{

        //Validation
        WorkoutDetails workoutDetails =  workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if(workoutDetails == null) throw new WorkoutDetailsNotFoundException();

        workoutDetailsRepository.unfavoriteWorkoutDetailsById(workoutId);
    }
}

package com.koleff.kare.workout.service;

import com.koleff.kare.common.error.exceptions.WorkoutConfigurationNotFoundException;
import com.koleff.kare.workout.mapper.WorkoutConfigurationMapper;
import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import com.koleff.kare.workout.repository.WorkoutConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutConfigurationServiceImpl implements WorkoutConfigurationService{

    private final WorkoutConfigurationRepository workoutConfigurationRepository;
    private final WorkoutConfigurationMapper workoutConfigurationMapper;

    @Autowired
    public WorkoutConfigurationServiceImpl(
            WorkoutConfigurationRepository workoutConfigurationRepository,
            WorkoutConfigurationMapper workoutConfigurationMapper
    ) {
        this.workoutConfigurationRepository = workoutConfigurationRepository;
        this.workoutConfigurationMapper = workoutConfigurationMapper;
    }

    @Override
    public WorkoutConfigurationDto getWorkoutConfiguration(Long workoutId) throws WorkoutConfigurationNotFoundException {
        WorkoutConfiguration workoutConfiguration = workoutConfigurationRepository.findByWorkoutDetailsId(workoutId);

        if(workoutConfiguration == null) throw new WorkoutConfigurationNotFoundException();

        return workoutConfigurationMapper.toDto(workoutConfiguration);
    }

    @Override
    public WorkoutConfigurationDto saveWorkoutConfiguration(WorkoutConfigurationDto configuration) {
        WorkoutConfiguration dbEntry = workoutConfigurationRepository.save(
                workoutConfigurationMapper.toEntity(configuration)
        );

        return workoutConfigurationMapper.toDto(dbEntry);
    }

    //TODO: refactor...
    @Override
    public void updateWorkoutConfiguration(WorkoutConfigurationDto configuration) {
        saveWorkoutConfiguration(configuration);
    }

    @Override
    public void deleteWorkoutConfiguration(WorkoutConfigurationDto configuration) {
        workoutConfigurationRepository.delete(
                workoutConfigurationMapper.toEntity(configuration)
        );
    }

    @Override
    public void deleteWorkoutConfiguration(Long workoutId) {
        workoutConfigurationRepository.deleteByWorkoutDetailsId(workoutId);
    }
}
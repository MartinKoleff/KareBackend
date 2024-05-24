package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.mapper.ExerciseDetailsMapper;
import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import com.koleff.kare.exercise.repository.ExerciseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ExerciseDetailsServiceImpl implements ExerciseDetailsService {

    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private final ExerciseDetailsMapper exerciseDetailsMapper;

    @Autowired
    public ExerciseDetailsServiceImpl(
            ExerciseDetailsRepository exerciseDetailsRepository,
            ExerciseDetailsMapper exerciseDetailsMapper
    ) {
        this.exerciseDetailsRepository = exerciseDetailsRepository;
        this.exerciseDetailsMapper = exerciseDetailsMapper;
    }

    @Override
    public ExerciseDetailsDto getExerciseDetails(Long exerciseId, Long workoutId) {
        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findByExerciseDetailsIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(() -> new NoSuchElementException(
                                "No exercise details found with exerciseId " + exerciseId + " and workoutId " + workoutId
                        )
                );


        return exerciseDetailsMapper.toDto(exerciseDetails);
    }

    @Override
    public ExerciseDetailsDto saveExerciseDetails(ExerciseDetailsDto exerciseDetailsDto) {
        ExerciseDetails dbEntry = exerciseDetailsRepository.save(
                exerciseDetailsMapper.toEntity(exerciseDetailsDto)
        );
        return exerciseDetailsMapper.toDto(dbEntry);
    }

    //TODO: refactor...
    @Override
    public ExerciseDetailsDto updateExerciseDetails(ExerciseDetailsDto exerciseDetails) {
        return saveExerciseDetails(exerciseDetails);
    }

    @Override
    public void deleteExerciseDetails(Long exerciseId, Long workoutId) {
        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findByExerciseDetailsIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(() -> new NoSuchElementException(
                                "No exercise details found with exerciseId " + exerciseId + " and workoutId " + workoutId
                        )
                );

        exerciseDetailsRepository.delete(exerciseDetails);
    }

    @Override
    public void deleteExerciseDetails(ExerciseDetailsDto exerciseDetails) {
        exerciseDetailsRepository.delete(
                exerciseDetailsMapper.toEntity(exerciseDetails)
        );
    }
}
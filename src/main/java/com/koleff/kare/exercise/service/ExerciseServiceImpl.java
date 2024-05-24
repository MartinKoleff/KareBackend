package com.koleff.kare.exercise.service;

import com.koleff.kare.common.Constants;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    @Autowired
    public ExerciseServiceImpl(
            ExerciseRepository exerciseRepository,
            ExerciseSetRepository exerciseSetRepository,
            ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public List<ExerciseDto> getCatalogExercises(Integer muscleGroupId) {
        return exerciseRepository
                .findByWorkoutIdAndMuscleGroupId(Constants.CATALOG_WORKOUT_ID, muscleGroupId)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();
    }

    public List<ExerciseDto> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .filter(exercise -> !exercise.getWorkoutId().equals(Constants.CATALOG_WORKOUT_ID))
                .map(exerciseMapper::toDto)
                .toList();
    }

    @Override
    public ExerciseDto getExercise(Long exerciseId, Long workoutId) {
        return exerciseRepository.findByExerciseIdAndWorkoutId(exerciseId, workoutId)
                .map(exerciseMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException(
                                String.format("No exercise found with exerciseId %d and workoutId %d", exerciseId, workoutId)
                        )
                );
    }

    public List<ExerciseDto> getAllCatalogExercises() {
        return exerciseRepository.findByWorkoutId(Constants.CATALOG_WORKOUT_ID)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();
    }

    public ExerciseDto saveExercise(ExerciseDto exercise) {
        Exercise dbEntry = exerciseRepository.save(
                exerciseMapper.toEntity(exercise)
        );

        return exerciseMapper.toDto(dbEntry);
    }

    //TODO: refactor...
    @Override
    public ExerciseDto updateExercise(ExerciseDto exercise) {
        return saveExercise(exercise);
    }

    public void deleteExercise(ExerciseDto exercise) {
        exerciseRepository.delete(
                exerciseMapper.toEntity(exercise)
        );
    }
}

package com.koleff.kare.exercise.service;

import com.koleff.kare.common.Constants;
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
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    private final ExerciseSetRepository exerciseSetRepository;

    @Autowired
    public ExerciseService(
            ExerciseRepository exerciseRepository,
            ExerciseSetRepository exerciseSetRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseSetRepository = exerciseSetRepository;
    }

    public List<Exercise> getCatalogExercises(Integer muscleGroupId) {
        return exerciseRepository.findByWorkoutIdAndMuscleGroupId(Constants.CATALOG_WORKOUT_ID, muscleGroupId);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .filter(exercise -> !exercise.getWorkoutId().equals(Constants.CATALOG_WORKOUT_ID))
                .toList();
    }

    public List<Exercise> getAllCatalogExercises() {
        return exerciseRepository.findByWorkoutId(Constants.CATALOG_WORKOUT_ID);
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    public ExerciseSet saveExerciseSet(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet);
    }

    public void deleteExerciseSet(ExerciseSet exerciseSet) {
        exerciseSetRepository.delete(exerciseSet);
    }

    public void deleteExerciseSetById(UUID setId) {
        exerciseSetRepository.deleteById(setId);
    }
}

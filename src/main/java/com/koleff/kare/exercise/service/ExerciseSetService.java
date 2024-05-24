package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;

    @Autowired
    public ExerciseSetService(ExerciseSetRepository exerciseSetRepository) {
        this.exerciseSetRepository = exerciseSetRepository;
    }

    public ExerciseSet getSetById(UUID setId) {
        return exerciseSetRepository.findById(setId).orElse(null);
    }

    public ExerciseSet saveExerciseSet(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet);
    }

    public List<ExerciseSet> saveAllExerciseSets(List<ExerciseSet> exerciseSets) {
        return exerciseSetRepository.saveAll(exerciseSets);
    }

    public void updateExerciseSet(ExerciseSet exerciseSet) {
        exerciseSetRepository.save(exerciseSet);
    }

    public void deleteExerciseSet(ExerciseSet exerciseSet) {
        exerciseSetRepository.delete(exerciseSet);
    }

    public void deleteSetById(UUID setId) {
        exerciseSetRepository.deleteById(setId);
    }
}
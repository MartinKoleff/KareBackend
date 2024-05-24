package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseConfigureServiceImpl implements ExerciseConfigureService{

    @Override
    public ExerciseDto addNewExerciseSet(Long exerciseId, Long workoutId, List<ExerciseSetDto> currentSets) {
        return null;
    }

    @Override
    public ExerciseDto deleteExerciseSet(Long exerciseId, Long workoutId, UUID setId) {
        return null;
    }
}

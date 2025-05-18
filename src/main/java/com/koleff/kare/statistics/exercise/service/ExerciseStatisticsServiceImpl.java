package com.koleff.kare.statistics.exercise.service;

import com.koleff.kare.common.error.exceptions.ExerciseSetNotFoundException;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.mapper.ExerciseSetMapper;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import com.koleff.kare.statistics.exercise.repository.ExerciseStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseStatisticsServiceImpl implements ExerciseStatisticsService {

    private final ExerciseStatisticsRepository exerciseStatisticsRepository;

    @Autowired
    public ExerciseStatisticsServiceImpl(
            ExerciseStatisticsRepository exerciseStatisticsRepository
    ) {
        this.exerciseStatisticsRepository = exerciseStatisticsRepository;
    }

    @Override
    public Float getPR(Long exerciseId) {
        return exerciseStatisticsRepository.findBestPRByExerciseId(exerciseId);
    }

    @Override
    public Float get1RepMax(Long exerciseId) {
        return exerciseStatisticsRepository.find1RepMaxByExerciseId(exerciseId);
    }

    @Override
    public Integer getTotalRepsPerformed(Long exerciseId) {
        return exerciseStatisticsRepository.findTotalRepsByExerciseId(exerciseId);
    }

    @Override
    public Integer getTotalSetsPerformed(Long exerciseId) {
        return exerciseStatisticsRepository.countSetsByExerciseId(exerciseId);
    }

    @Override
    public Float getTotalWeightLifted(Long exerciseId) {
        return exerciseStatisticsRepository.findTotalWeightLiftedByExerciseId(exerciseId);
    }
}
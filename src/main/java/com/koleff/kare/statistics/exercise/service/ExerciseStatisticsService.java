package com.koleff.kare.statistics.exercise.service;

public interface ExerciseStatisticsService {

    Float getPR(Long exerciseId);

    Float get1RepMax(Long exerciseId);

    Integer getTotalRepsPerformed(Long exerciseId);

    Integer getTotalSetsPerformed(Long exerciseId);

    Float getTotalWeightLifted(Long exerciseId);
}

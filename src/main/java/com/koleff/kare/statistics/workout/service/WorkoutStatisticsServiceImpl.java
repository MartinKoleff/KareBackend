package com.koleff.kare.statistics.workout.service;

import com.koleff.kare.statistics.exercise.repository.ExerciseStatisticsRepository;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;
import com.koleff.kare.statistics.workout.repository.WorkoutStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkoutStatisticsServiceImpl implements WorkoutStatisticsService {

    private final WorkoutStatisticsRepository workoutStatisticsRepository;

    @Autowired
    public WorkoutStatisticsServiceImpl(WorkoutStatisticsRepository workoutStatisticsRepository) {
        this.workoutStatisticsRepository = workoutStatisticsRepository;
    }

    @Override
    public WorkoutTotalTimesCompleted getTotalTimesCompleted(Long workoutId) {
        Integer streak = workoutStatisticsRepository.getTotalWorkoutsCompleted(workoutId);
        List<Date> datesOfCompletion = workoutStatisticsRepository.getDatesOfCompletionForWorkout(workoutId);

        return new WorkoutTotalTimesCompleted(
                streak,
                datesOfCompletion
        );
    }

    @Override
    public Float getTotalWeightLifted(Long workoutId) {
        return workoutStatisticsRepository.getTotalWeightLiftedForWorkout(workoutId);
    }

    @Override
    public List<Date> getDatesOfCompletionForWorkout(Long workoutId) {
        return workoutStatisticsRepository.getDatesOfCompletionForWorkout(workoutId);
    }

    @Override
    public Integer getTotalRepsPerformed(Long workoutId) {
        return workoutStatisticsRepository.getTotalRepsPerformedForWorkout(workoutId);
    }

    @Override
    public Integer getTotalSetsPerformed(Long workoutId) {
        return workoutStatisticsRepository.getTotalSetsPerformedForWorkout(workoutId);
    }
}
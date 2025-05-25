package com.koleff.kare.do_workout_performance_metrics.configuration;

import com.koleff.kare.common.DateUtils;
import com.koleff.kare.common.PerformanceMetricsGenerator;
import com.koleff.kare.common.WorkoutGenerator;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;
import com.koleff.kare.do_workout_performance_metrics.repository.DoWorkoutExerciseSetRepository;
import com.koleff.kare.do_workout_performance_metrics.repository.DoWorkoutPerformanceMetricsRepository;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class PerformanceMetricsDBConfiguration {
    private final DoWorkoutPerformanceMetricsRepository doWorkoutPerformanceMetricsRepository;
    private final DoWorkoutExerciseSetRepository doWorkoutExerciseSetRepository;

    @Autowired
    public PerformanceMetricsDBConfiguration(
            DoWorkoutPerformanceMetricsRepository doWorkoutPerformanceMetricsRepository,
            DoWorkoutExerciseSetRepository doWorkoutExerciseSetRepository
    ) {
        this.doWorkoutPerformanceMetricsRepository = doWorkoutPerformanceMetricsRepository;
        this.doWorkoutExerciseSetRepository = doWorkoutExerciseSetRepository;
    }

    @Bean
    public CommandLineRunner initializePerformanceMetricsDatabase() {
        return args -> {
            List<WorkoutDetails> workoutDetailsList = WorkoutGenerator.generateWorkoutDetails();

            AtomicLong counter = new AtomicLong(1L);
            List<Date> dates = List.of(
                    DateUtils.getTodayDate(),
                    DateUtils.getYesterdayDate(),
                    DateUtils.getRandomDate()
            );
             workoutDetailsList.forEach(
                    workoutDetails -> {
                        DoWorkoutPerformanceMetrics doWorkoutPerformanceMetrics = PerformanceMetricsGenerator.generatePerformanceMetrics(
                                counter.getAndIncrement(),
                                workoutDetails.getWorkoutDetailsId(),
                                MuscleGroup.fromId(workoutDetails.getMuscleGroupId()),
                                dates.get((int) (counter.get() - 2)),
                                workoutDetails.getExercises()
                        );

                        doWorkoutPerformanceMetricsRepository.save(doWorkoutPerformanceMetrics);
                        doWorkoutExerciseSetRepository.saveAll(doWorkoutPerformanceMetrics.getDoWorkoutExerciseSets());
                    }
            );
        };
    }
}

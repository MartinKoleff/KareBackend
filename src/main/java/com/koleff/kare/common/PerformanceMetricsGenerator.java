package com.koleff.kare.common;

import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutExerciseSet;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;
import com.koleff.kare.do_workout_performance_metrics.models.entity.ExerciseTime;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.MuscleGroup;

import java.util.*;
import java.util.stream.Collectors;

public class PerformanceMetricsGenerator {
    public static DoWorkoutPerformanceMetrics generatePerformanceMetrics(
            Long performanceMetricsId,
            Long workoutId,
            MuscleGroup selectedMuscleGroup,
            Date selectedDate, //Can be null
            List<Exercise> exercises //Can be null
    ) {
        if (selectedDate == null) {
            selectedDate = DateUtils.getRandomDate();
        }

        if (exercises == null || exercises.isEmpty()) {
            exercises = ExerciseGenerator.loadExercises(selectedMuscleGroup, workoutId);
        }

        List<DoWorkoutExerciseSet> doWorkoutExerciseSets = generateDoWorkoutExerciseSets(
                performanceMetricsId,
                selectedMuscleGroup,
                selectedDate,
                workoutId,
                exercises.size(),
                exercises
        );

        doWorkoutExerciseSets.forEach(doWorkoutExerciseSet -> {
            doWorkoutExerciseSet.setDoWorkoutPerformanceMetricsId(performanceMetricsId);
        });

        return new DoWorkoutPerformanceMetrics(
                performanceMetricsId,
                workoutId,
                selectedDate,
                doWorkoutExerciseSets
        );
    }

    public static List<DoWorkoutExerciseSet> generateDoWorkoutExerciseSets(
            Long performanceMetricsId,
            MuscleGroup muscleGroup,
            Date selectedDate, //Can be null
            Long workoutId,
            int numberOfExercises,
            List<Exercise> exercises //Can be null
    ) {
        List<Exercise> selectedExercises;

        if (exercises != null && !exercises.isEmpty()) {
            //Shuffle and take numberOfExercises
            List<Exercise> mutableExercises = new ArrayList<>(exercises);
            Collections.shuffle(mutableExercises);
            selectedExercises = mutableExercises.stream()
                    .limit(numberOfExercises)
                    .collect(Collectors.toList());
        } else {
            //Load exercises with sets from generator
            List<Exercise> loadedExercises = ExerciseGenerator.loadExercises(muscleGroup, workoutId);

            Collections.shuffle(loadedExercises);
            selectedExercises = loadedExercises.stream()
                    .limit(numberOfExercises)
                    .collect(Collectors.toList());
        }

        if (selectedDate == null) {
            selectedDate = DateUtils.getRandomDate();
        }
        Date finalSelectedDate = selectedDate;
        ExerciseTime defaultTime = new ExerciseTime(0, 0, 10);

        List<DoWorkoutExerciseSet> doWorkoutExerciseSets = new ArrayList<>();

        selectedExercises.forEach(exercise -> {
//            List<ExerciseSet> exerciseSetsList = ExerciseGenerator.generateExerciseSets(exercise.getExerciseId(), exercise.getWorkoutId(), 4);
//            exerciseSetsList.forEach(set -> set.setExercise(exercise));
//            exercise.setSets(exerciseSetsList);

            exercise.getSets().forEach(exerciseSet -> {
                DoWorkoutExerciseSet doWorkoutExerciseSet = new DoWorkoutExerciseSet();
                doWorkoutExerciseSet.setInstanceId(UUID.randomUUID().toString());
                doWorkoutExerciseSet.setDoWorkoutPerformanceMetricsId(performanceMetricsId);
                doWorkoutExerciseSet.setWorkoutId(workoutId);
                doWorkoutExerciseSet.setExerciseId(exercise.getExerciseId());
                doWorkoutExerciseSet.setTemplateSetId(exerciseSet.getExerciseSetId());
                doWorkoutExerciseSet.setReps(randomIntBetween(6, 15));
                doWorkoutExerciseSet.setWeight((float) randomIntBetween(15, 100));
                doWorkoutExerciseSet.setIsDone(false); //new Random().nextBoolean()
                doWorkoutExerciseSet.setTime(defaultTime);
                doWorkoutExerciseSet.setDate(finalSelectedDate);

                doWorkoutExerciseSets.add(doWorkoutExerciseSet);
            });
        });

        return doWorkoutExerciseSets;
    }

    private static int randomIntBetween(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}

package com.koleff.kare.common;

import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import com.koleff.kare.workout.models.entity.WorkoutDetails;

import java.util.ArrayList;
import java.util.List;

public class WorkoutGenerator {

    public static final int TOTAL_WORKOUTS = 3;

    private static final String description =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc interdum nibh nec pharetra iaculis. Aenean ultricies egestas leo at ultricies. Quisque suscipit, purus ut congue porta, eros eros tincidunt sem, sed commodo magna metus eu nibh. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum quis velit eget eros malesuada luctus. Suspendisse iaculis ullamcorper condimentum. Sed metus augue, dapibus eu venenatis vitae, ornare non turpis. Donec suscipit iaculis dolor, id fermentum mauris interdum in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.";

    private static final List<WorkoutDetails> workoutDetailsList = new ArrayList<>();
    private static final List<Workout> workoutsList = new ArrayList<>();

    public static List<Workout> generateWorkouts() {
        if (workoutsList.isEmpty()) {
            workoutsList.add(
                    new Workout(
                            1L,
                            "Arnold chest workout",
                            MuscleGroup.CHEST.getMuscleGroupId(),
                            "",
                            ExerciseGenerator.getTotalExercisesForMuscleGroup(MuscleGroup.CHEST),
                            false,
                            null
                    )
            );
            workoutsList.add(
                    new Workout(
                            2L,
                            "Chavdo destroy back workout",
                            MuscleGroup.BACK.getMuscleGroupId(),
                            "",
                            ExerciseGenerator.getTotalExercisesForMuscleGroup(MuscleGroup.BACK),
                            false,
                            null
                    )
            );
            workoutsList.add(
                    new Workout(
                            3L,
                            "Blow your arms workout",
                            MuscleGroup.ARMS.getMuscleGroupId(),
                            "",
                            ExerciseGenerator.getTotalExercisesForMuscleGroup(MuscleGroup.ARMS),
                            true,
                            null
                    )
            );
        }

        return workoutsList;
    }

    public static List<WorkoutDetails> generateWorkoutDetails() {
        if (workoutDetailsList.isEmpty()) {
            if (workoutsList.isEmpty()) {
                generateWorkouts();
            }
            WorkoutDetails workoutDetails1 = new WorkoutDetails(
                    1L,
                    "Arnold chest workout",
                    "Blow your chest",
                    MuscleGroup.CHEST.getMuscleGroupId(),
                    false,
                    workoutsList.get(0),
                    ExerciseGenerator.loadExercises(MuscleGroup.CHEST, 1L),
                    generateWorkoutConfiguration(1L)
            );
            workoutDetailsList.add(workoutDetails1);

            WorkoutDetails workoutDetails2 = new WorkoutDetails(
                    2L,
                    "Chavdo destroy back workout",
                    "Blow your back with me4ka",
                    MuscleGroup.BACK.getMuscleGroupId(),
                    false,
                    workoutsList.get(1),
                    ExerciseGenerator.loadExercises(MuscleGroup.BACK, 2L),
                    generateWorkoutConfiguration(2L)
            );
            workoutDetailsList.add(workoutDetails2);

            WorkoutDetails workoutDetails3 = new WorkoutDetails(
                    3L,
                    "Blow your arms workout",
                    "Blow your arms with curls",
                    MuscleGroup.ARMS.getMuscleGroupId(),
                    true,
                    workoutsList.get(2),
                    ExerciseGenerator.loadExercises(MuscleGroup.BACK, 3L),
                    generateWorkoutConfiguration(3L)
            );
            workoutDetailsList.add(workoutDetails3);
        }

        return workoutDetailsList;
    }

    private static WorkoutConfiguration generateWorkoutConfiguration(Long workoutDetailsId) {
        return new WorkoutConfiguration(
                0L, //Auto Generate...
                workoutDetailsId,
                "00:00:10",
                null
        );
    }

    public static Workout generateCatalogWorkout() {
        return new Workout(
                Constants.CATALOG_WORKOUT_ID,
                "Catalog workout",
                MuscleGroup.NONE.getMuscleGroupId(),
                "",
                ExerciseGenerator.getTotalExercisesForMuscleGroup(MuscleGroup.ALL),
                false,
                null
        );
    }

    public static WorkoutDetails generateCatalogWorkoutDetails() {
        return new WorkoutDetails(
                Constants.CATALOG_WORKOUT_ID,
                "Catalog workout details",
                "",
                MuscleGroup.NONE.getMuscleGroupId(),
                false,
                null,
                null,
                null
        );
    }
}

package com.koleff.kare.common;

import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import com.koleff.kare.exercise.models.entity.MachineType;
import com.koleff.kare.exercise.models.entity.MuscleGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ExerciseGenerator {

    public static final int TOTAL_EXERCISES = 60;

    private static final String description =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc interdum nibh nec pharetra iaculis. Aenean ultricies egestas leo at ultricies. Quisque suscipit, purus ut congue porta, eros eros tincidunt sem, sed commodo magna metus eu nibh. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum quis velit eget eros malesuada luctus. Suspendisse iaculis ullamcorper condimentum. Sed metus augue, dapibus eu venenatis vitae, ornare non turpis. Donec suscipit iaculis dolor, id fermentum mauris interdum in. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.";
    private static final String videoUrl = "dQw4w9WgXcQ"; // https://www.youtube.com/watch?v=
    private static final String snapshot = "snapshot.jpg"; //TODO: update in future...

    public static final List<MuscleGroup> SUPPORTED_MUSCLE_GROUPS = List.of(
            MuscleGroup.CHEST,
            MuscleGroup.BACK,
            MuscleGroup.TRICEPS,
            MuscleGroup.BICEPS,
            MuscleGroup.ARMS,
            MuscleGroup.SHOULDERS,
            MuscleGroup.LEGS,
            MuscleGroup.FULL_BODY
    );

    public static List<Exercise> loadExercises(MuscleGroup muscleGroup) {
        Long customWorkoutId = Constants.CATALOG_WORKOUT_ID;

        switch (muscleGroup) {
            case CHEST:
                return getChestExercises(customWorkoutId);
            case BACK:
                return getBackExercises(customWorkoutId);
            case TRICEPS:
                return getTricepsExercises(customWorkoutId);
            case BICEPS:
                return getBicepsExercises(customWorkoutId);
            case SHOULDERS:
                return getShoulderExercises(customWorkoutId);
            case LEGS:
                return getLegsExercises(customWorkoutId);
            case ARMS: {
                List<Exercise> exercises = new ArrayList<>();
                exercises.addAll(getBicepsExercises(customWorkoutId));
                exercises.addAll(getTricepsExercises(customWorkoutId));
                return exercises;
            }
            default:
                return List.of();
        }
    }

    public static List<ExerciseDetails> loadExerciseDetails(MuscleGroup muscleGroup) {
        Long customWorkoutId = Constants.CATALOG_WORKOUT_ID;

        return switch (muscleGroup) {
            case CHEST -> getChestExerciseDetails(customWorkoutId);
            case BACK -> getBackExerciseDetails(customWorkoutId);
            case TRICEPS -> getTricepsExerciseDetails(customWorkoutId);
            case BICEPS -> getBicepsExerciseDetails(customWorkoutId);
            case SHOULDERS -> getShoulderExerciseDetails(customWorkoutId);
            case LEGS -> getLegsExerciseDetails(customWorkoutId);
            default -> List.of();
        };
    }

    public static List<ExerciseDto> getAllExercises() {
        List<ExerciseDto> exercisesList = new ArrayList<>();

        for (MuscleGroup muscleGroup : SUPPORTED_MUSCLE_GROUPS) {
            List<Exercise> generatedExercises = loadExercises(muscleGroup);

            for (Exercise exercise : generatedExercises) {
                ExerciseDto exerciseDto = new ExerciseDto(
                        exercise.getExerciseId(),
                        exercise.getWorkoutId(),
                        exercise.getName(),
                        exercise.getMuscleGroupId(),
                        exercise.getMachineTypeId(),
                        exercise.getSnapshot(),
                        null
                );
                exercisesList.add(exerciseDto);
            }
        }

        return exercisesList;
    }

    public static List<ExerciseDetailsDto> getAllExerciseDetails() {
        List<ExerciseDetailsDto> exercisesDetailsList = new ArrayList<>();

        for (MuscleGroup muscleGroup : SUPPORTED_MUSCLE_GROUPS) {
            List<ExerciseDetails> generatedExerciseDetails = loadExerciseDetails(muscleGroup);

            for (ExerciseDetails exerciseDetails : generatedExerciseDetails) {
                ExerciseDetailsDto exerciseDetailsDto = new ExerciseDetailsDto(
                        exerciseDetails.getExerciseDetailsId(),
                        exerciseDetails.getWorkoutId(),
                        exerciseDetails.getName(),
                        exerciseDetails.getDescription(),
                        exerciseDetails.getMuscleGroupId(),
                        exerciseDetails.getMachineTypeId(),
                        exerciseDetails.getSnapshot(),
                        exerciseDetails.getVideoUrl()
                );
                exercisesDetailsList.add(exerciseDetailsDto);
            }
        }

        return exercisesDetailsList;
    }

    private static List<Exercise> getLegsExercises(Long customWorkoutId) {
        return List.of(
                new Exercise(51L, customWorkoutId, "Squat", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(52L, customWorkoutId, "Bulgarian split squad", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(53L, customWorkoutId, "Smith machine squad", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(54L, customWorkoutId, "Leg extension", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(55L, customWorkoutId, "Kettlebell walking lunges", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(56L, customWorkoutId, "Leg press", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(57L, customWorkoutId, "Prone leg curl", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(58L, customWorkoutId, "Seated calf raises", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(59L, customWorkoutId, "Standing calf raises", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot),
                new Exercise(60L, customWorkoutId, "Barbell standing calf raises", MuscleGroup.LEGS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot)
        );
    }

    private static List<ExerciseDetails> getLegsExerciseDetails(Long customWorkoutId) {
        return List.of(
                new ExerciseDetails(51L, customWorkoutId, "Squat", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(52L, customWorkoutId, "Bulgarian split squad", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(53L, customWorkoutId, "Smith machine squad", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(54L, customWorkoutId, "Leg extension", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(55L, customWorkoutId, "Kettlebell walking lunges", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(56L, customWorkoutId, "Leg press", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(57L, customWorkoutId, "Prone leg curl", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(58L, customWorkoutId, "Seated calf raises", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(59L, customWorkoutId, "Standing calf raises", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(60L, customWorkoutId, "Barbell standing calf raises", description, MuscleGroup.LEGS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl)
        );
    }

    private static List<Exercise> getChestExercises(Long customWorkoutId) {
        return List.of(
                new Exercise(1L, customWorkoutId, "Flat barbell bench press", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(2L, customWorkoutId, "Incline barbell bench press", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(3L, customWorkoutId, "Incline barbell bench press", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(4L, customWorkoutId, "Incline dumbbell bench press", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(5L, customWorkoutId, "Flat dumbbell bench press", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(6L, customWorkoutId, "Pec deck fly", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(7L, customWorkoutId, "Cable chest fly", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(8L, customWorkoutId, "Hammer strength", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(9L, customWorkoutId, "Dips", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot),
                new Exercise(10L, customWorkoutId, "Push ups", MuscleGroup.CHEST.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot)
        );
    }

    private static List<ExerciseDetails> getChestExerciseDetails(Long customWorkoutId) {
        return List.of(
                new ExerciseDetails(1L, customWorkoutId, "Flat barbell bench press", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(2L, customWorkoutId, "Incline barbell bench press", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(3L, customWorkoutId, "Incline barbell bench press", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(4L, customWorkoutId, "Incline dumbbell bench press", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(5L, customWorkoutId, "Flat dumbbell bench press", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(6L, customWorkoutId, "Pec deck fly", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(7L, customWorkoutId, "Cable chest fly", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(8L, customWorkoutId, "Hammer strength", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(9L, customWorkoutId, "Dips", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(10L, customWorkoutId, "Push ups", description, MuscleGroup.CHEST.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl)
        );
    }

    private static List<Exercise> getBackExercises(Long customWorkoutId) {
        return List.of(
                new Exercise(11L, customWorkoutId, "Seated cable rows", MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(12L, customWorkoutId, "Lat pulldown (Wide grip)", MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(13L, customWorkoutId, "Pull ups", MuscleGroup.BACK.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot),
                new Exercise(14L, customWorkoutId, "Bent over barbell row", MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(15L, customWorkoutId, "Deadlift", MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(16L, customWorkoutId, "Bent over dumbbell row", MuscleGroup.BACK.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(17L, customWorkoutId, "Standing lat pulldown", MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(18L, customWorkoutId, "T-bar row", MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(19L, customWorkoutId, "Dumbbell Shrugs", MuscleGroup.BACK.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(20L, customWorkoutId, "Behind the neck lat pulldown", MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(21L, customWorkoutId, "Romanian deadlift", MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot)
        );
    }

    private static List<ExerciseDetails> getBackExerciseDetails(Long customWorkoutId) {
        return List.of(
                new ExerciseDetails(11L, customWorkoutId, "Seated cable rows", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(12L, customWorkoutId, "Lat pulldown (Wide grip)", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(13L, customWorkoutId, "Pull ups", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(14L, customWorkoutId, "Bent over barbell row", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(15L, customWorkoutId, "Deadlift", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(16L, customWorkoutId, "Bent over dumbbell row", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(17L, customWorkoutId, "Standing lat pulldown", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(18L, customWorkoutId, "T-bar row", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(19L, customWorkoutId, "Dumbbell Shrugs", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(20L, customWorkoutId, "Behind the neck lat pulldown", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(21L, customWorkoutId, "Romanian deadlift", description, MuscleGroup.BACK.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl)
        );
    }

    private static List<Exercise> getTricepsExercises(Long customWorkoutId) {
        return List.of(
                new Exercise(22L, customWorkoutId, "Triceps cable pushdown", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(23L, customWorkoutId, "Dumbbell triceps kickback", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(24L, customWorkoutId, "Skull crushers", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(25L, customWorkoutId, "Dips", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot),
                new Exercise(26L, customWorkoutId, "Machine triceps dips", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(27L, customWorkoutId, "Dumbbell triceps extension", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(28L, customWorkoutId, "Cable rope triceps pushdown", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(29L, customWorkoutId, "Bench dip", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot),
                new Exercise(30L, customWorkoutId, "Barbell standing french press", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(31L, customWorkoutId, "Triceps cable rope extension", MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot)
        );
    }

    private static List<ExerciseDetails> getTricepsExerciseDetails(Long customWorkoutId) {
        return List.of(
                new ExerciseDetails(22L, customWorkoutId, "Triceps cable pushdown", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(23L, customWorkoutId, "Dumbbell triceps kickback", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(24L, customWorkoutId, "Skull crushers", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(25L, customWorkoutId, "Dips", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(26L, customWorkoutId, "Machine triceps dips", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(27L, customWorkoutId, "Dumbbell triceps extension", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(28L, customWorkoutId, "Cable rope triceps pushdown", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(29L, customWorkoutId, "Bench dip", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(30L, customWorkoutId, "Barbell standing french press", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(31L, customWorkoutId, "Triceps cable rope extension", description, MuscleGroup.TRICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl)
        );
    }

    private static List<Exercise> getBicepsExercises(Long customWorkoutId) {
        return List.of(
                new Exercise(32L, customWorkoutId, "Standing dumbbell biceps curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(33L, customWorkoutId, "Sitting dumbbell biceps curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(34L, customWorkoutId, "Barbell biceps curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(35L, customWorkoutId, "Dumbbell concentrated curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(36L, customWorkoutId, "Dumbbell hammer curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(37L, customWorkoutId, "Dumbbell hammer curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(38L, customWorkoutId, "One arm dumbbell preacher curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(39L, customWorkoutId, "Barbell preacher curl", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(40L, customWorkoutId, "Reverse grip biceps curl at the low pulley cable", MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot)
        );
    }

    private static List<ExerciseDetails> getBicepsExerciseDetails(Long customWorkoutId) {
        return List.of(
                new ExerciseDetails(32L, customWorkoutId, "Standing dumbbell biceps curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(33L, customWorkoutId, "Sitting dumbbell biceps curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(34L, customWorkoutId, "Barbell biceps curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(35L, customWorkoutId, "Dumbbell concentrated curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(36L, customWorkoutId, "Dumbbell hammer curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(37L, customWorkoutId, "Dumbbell hammer curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(38L, customWorkoutId, "One arm dumbbell preacher curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(39L, customWorkoutId, "Barbell preacher curl", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(40L, customWorkoutId, "Reverse grip biceps curl at the low pulley cable", description, MuscleGroup.BICEPS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl)
        );
    }

    private static List<Exercise> getShoulderExercises(Long customWorkoutId) {
        return List.of(
                new Exercise(41L, customWorkoutId, "Barbell upright row", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(42L, customWorkoutId, "Dumbbell front raises", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(43L, customWorkoutId, "Dumbbell lateral raises", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(44L, customWorkoutId, "Seated dumbbell shoulder press", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot),
                new Exercise(45L, customWorkoutId, "Barbell shoulder press", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot),
                new Exercise(46L, customWorkoutId, "Face pull", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(47L, customWorkoutId, "Front plate raise", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot),
                new Exercise(48L, customWorkoutId, "One arm lateral raises at the low pulley cable", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(49L, customWorkoutId, "Reverse pec deck", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot),
                new Exercise(50L, customWorkoutId, "Dumbbell behind the back press", MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot)
        );
    }

    private static List<ExerciseDetails> getShoulderExerciseDetails(Long customWorkoutId) {
        return List.of(
                new ExerciseDetails(41L, customWorkoutId, "Barbell upright row", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(42L, customWorkoutId, "Dumbbell front raises", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(43L, customWorkoutId, "Dumbbell lateral raises", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(44L, customWorkoutId, "Seated dumbbell shoulder press", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(45L, customWorkoutId, "Barbell shoulder press", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.BARBELL.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(46L, customWorkoutId, "Face pull", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(47L, customWorkoutId, "Front plate raise", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.CALISTHENICS.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(48L, customWorkoutId, "One arm lateral raises at the low pulley cable", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(49L, customWorkoutId, "Reverse pec deck", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.MACHINE.getMachineTypeId(), snapshot, videoUrl),
                new ExerciseDetails(50L, customWorkoutId, "Dumbbell behind the back press", description, MuscleGroup.SHOULDERS.getMuscleGroupId(), MachineType.DUMBBELL.getMachineTypeId(), snapshot, videoUrl)
        );
    }
}

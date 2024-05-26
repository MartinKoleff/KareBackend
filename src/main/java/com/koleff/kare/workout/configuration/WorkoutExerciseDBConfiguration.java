package com.koleff.kare.workout.configuration;

import com.koleff.kare.common.Constants;
import com.koleff.kare.common.ExerciseGenerator;
import com.koleff.kare.exercise.mapper.ExerciseDetailsMapper;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.exercise.repository.ExerciseDetailsRepository;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutDetailsRepository;
import com.koleff.kare.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.List;

@Configuration
public class WorkoutExerciseDBConfiguration {
    private final WorkoutRepository workoutRepository;
    private final WorkoutDetailsRepository workoutDetailsRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private final ExerciseMapper exerciseMapper;
    private final ExerciseDetailsMapper exerciseDetailsMapper;


    @Autowired
    public WorkoutExerciseDBConfiguration(
            WorkoutRepository workoutRepository,
            WorkoutDetailsRepository workoutDetailsRepository,
            ExerciseRepository exerciseRepository,
            ExerciseDetailsRepository exerciseDetailsRepository,
            ExerciseMapper exerciseMapper,
            ExerciseDetailsMapper exerciseDetailsMapper
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseDetailsRepository = exerciseDetailsRepository;
        this.exerciseMapper = exerciseMapper;
        this.exerciseDetailsMapper = exerciseDetailsMapper;
    }

    @Bean
    public CommandLineRunner initializeWorkoutAndExerciseDatabase() {
        return args -> {

            //Catalog workout
            Workout catalogWorkout = new Workout(
                    Constants.CATALOG_WORKOUT_ID,
                    "Catalog workout",
                    MuscleGroup.NONE.getMuscleGroupId(),
                    "",
                    60, //TODO: update to MuscleGroup.ALL.getTotalExercises()
                    false,
                    null
            );

            WorkoutDetails catalogWorkoutDetails = new WorkoutDetails(
                    Constants.CATALOG_WORKOUT_ID,
                    "Catalog workout details",
                    "",
                    MuscleGroup.NONE.getMuscleGroupId(),
                    false,
                    null,
                    null,
                    null
            );

            workoutRepository.save(catalogWorkout);
            workoutDetailsRepository.save(catalogWorkoutDetails);

            //Exercises
            List<Exercise> exerciseList = ExerciseGenerator.getAllExercises()
                    .stream()
                    .map(exerciseMapper::toEntity)
                    .toList();

            List<ExerciseDetails> exerciseDetailsList = ExerciseGenerator.getAllExerciseDetails()
                    .stream()
                    .map(exerciseDetailsMapper::toEntity)
                    .toList();

            exerciseRepository.saveAll(exerciseList);
            exerciseDetailsRepository.saveAll(exerciseDetailsList);
        };
    }
}

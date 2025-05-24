package com.koleff.kare.exercise.configuration;

import com.koleff.kare.common.ExerciseGenerator;
import com.koleff.kare.common.WorkoutGenerator;
import com.koleff.kare.exercise.mapper.ExerciseDetailsMapper;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
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
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class CatalogExerciseDBConfiguration {
    private final WorkoutRepository workoutRepository;
    private final WorkoutDetailsRepository workoutDetailsRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private final ExerciseMapper exerciseMapper;
    private final ExerciseDetailsMapper exerciseDetailsMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatalogExerciseDBConfiguration(
            WorkoutRepository workoutRepository,
            WorkoutDetailsRepository workoutDetailsRepository,
            ExerciseRepository exerciseRepository,
            ExerciseDetailsRepository exerciseDetailsRepository,
            ExerciseMapper exerciseMapper,
            ExerciseDetailsMapper exerciseDetailsMapper,
            JdbcTemplate jdbcTemplate
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseDetailsRepository = exerciseDetailsRepository;
        this.exerciseMapper = exerciseMapper;
        this.exerciseDetailsMapper = exerciseDetailsMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public CommandLineRunner initializeCatalogExerciseDatabase() {
        return args -> {

            //Catalog workout
            Workout catalogWorkout = WorkoutGenerator.generateCatalogWorkout();

            WorkoutDetails catalogWorkoutDetails = WorkoutGenerator.generateCatalogWorkoutDetails();

//            workoutRepository.saveAndFlush(catalogWorkout);
//            workoutDetailsRepository.saveAndFlush(catalogWorkoutDetails);

            String workoutSql = "INSERT INTO workout_table (workout_id, name, muscle_group, snapshot, total_exercises, is_favorite) VALUES (?, ?, ?, ?, ?, ?)";
            String workoutDetailsSql = "INSERT INTO workout_details_table (workout_details_id, name, description, muscle_group, is_favorite) VALUES (?, ?, ?, ?, ?)";

            jdbcTemplate.update(workoutSql,
                    catalogWorkout.getWorkoutId(),
                    catalogWorkout.getName(),
                    catalogWorkout.getMuscleGroupId(),
                    catalogWorkout.getSnapshot(),
                    catalogWorkout.getTotalExercises(),
                    catalogWorkout.getIsFavorite()
            );

            jdbcTemplate.update(workoutDetailsSql,
                    catalogWorkoutDetails.getWorkoutDetailsId(),
                    catalogWorkoutDetails.getName(),
                    catalogWorkoutDetails.getDescription(),
                    catalogWorkoutDetails.getMuscleGroupId(),
                    catalogWorkoutDetails.getIsFavorite()
            );

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

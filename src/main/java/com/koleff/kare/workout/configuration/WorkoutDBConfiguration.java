package com.koleff.kare.workout.configuration;

import com.koleff.kare.common.ExerciseGenerator;
import com.koleff.kare.common.WorkoutGenerator;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutConfigurationRepository;
import com.koleff.kare.workout.repository.WorkoutDetailsRepository;
import com.koleff.kare.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class WorkoutDBConfiguration {
    private final WorkoutRepository workoutRepository;
    private final WorkoutDetailsRepository workoutDetailsRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseSetRepository exerciseSetRepository;
    private final WorkoutConfigurationRepository workoutConfigurationRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkoutDBConfiguration(
            WorkoutRepository workoutRepository,
            WorkoutDetailsRepository workoutDetailsRepository,
            ExerciseRepository exerciseRepository,
            ExerciseSetRepository exerciseSetRepository, WorkoutConfigurationRepository workoutConfigurationRepository,
            JdbcTemplate jdbcTemplate
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseSetRepository = exerciseSetRepository;
        this.workoutConfigurationRepository = workoutConfigurationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public CommandLineRunner initializeWorkoutDatabase() {
        return args -> {
            String workoutSql = "INSERT INTO workout_table (workout_id, name, muscle_group, snapshot, total_exercises, is_favorite) VALUES (?, ?, ?, ?, ?, ?)";
            String workoutDetailsSql = "INSERT INTO workout_details_table (workout_details_id, name, description, muscle_group, is_favorite) VALUES (?, ?, ?, ?, ?)";

            List<Workout> workoutsList = WorkoutGenerator.generateWorkouts();
            List<WorkoutDetails> workoutDetailsList = WorkoutGenerator.generateWorkoutDetails();

            workoutsList.forEach(
                    workout ->
                            jdbcTemplate.update(workoutSql,
                                    workout.getWorkoutId(),
                                    workout.getName(),
                                    workout.getMuscleGroupId(),
                                    workout.getSnapshot(),
                                    workout.getTotalExercises(),
                                    workout.getIsFavorite()
                            )
            );

            workoutDetailsList.forEach(
                    workoutDetails -> {
                        jdbcTemplate.update(workoutDetailsSql,
                                workoutDetails.getWorkoutDetailsId(),
                                workoutDetails.getName(),
                                workoutDetails.getDescription(),
                                workoutDetails.getMuscleGroupId(),
                                workoutDetails.getIsFavorite()
                        );

                        workoutConfigurationRepository.save(workoutDetails.getConfiguration());

                        workoutDetails.getExercises().forEach(
                                exercise -> {
                                    exercise.setWorkoutDetails(workoutDetails);
//
//                                    List< ExerciseSet> exerciseSetsList = ExerciseGenerator.generateExerciseSets(exercise.getExerciseId(), exercise.getWorkoutId(), 4);
//                                    exerciseSetsList.forEach(set -> set.setExercise(exercise));
//                                    exercise.setSets(exerciseSetsList);
                                    exerciseRepository.save(exercise);

                                    exercise.getSets().forEach(
                                            exerciseSet -> {
//                                                exerciseSet.setExercise(exercise);
                                                exerciseSetRepository.save(exerciseSet);
                                            }
                                    );
                                }
                        );
                    }
            );
        };
    }
}

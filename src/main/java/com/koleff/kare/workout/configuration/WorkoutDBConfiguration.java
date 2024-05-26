package com.koleff.kare.workout.configuration;

import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutDetailsRepository;
import com.koleff.kare.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkoutDBConfiguration {
    private final WorkoutRepository workoutRepository;
    private final WorkoutDetailsRepository workoutDetailsRepository;

    @Autowired
    public WorkoutDBConfiguration(
            WorkoutRepository workoutRepository,
            WorkoutDetailsRepository workoutDetailsRepository
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutDetailsRepository = workoutDetailsRepository;
    }

    @Bean
    public CommandLineRunner initializeWorkoutDatabase() {
        return args -> {

            //Catalog workout
            Workout catalogWorkout = new Workout(
                    0L,
                    "Catalog workout",
                    MuscleGroup.NONE.getMuscleGroupId(),
                    "",
                    60, //TODO: update to MuscleGroup.ALL.getTotalExercises()
                    false,
                    null
            );

            WorkoutDetails catalogWorkoutDetails = new WorkoutDetails(
                    0L,
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
        };
    }
}

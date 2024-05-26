package com.koleff.kare.exercise.configuration;

import com.koleff.kare.common.ExerciseGenerator;
import com.koleff.kare.exercise.mapper.ExerciseDetailsMapper;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import com.koleff.kare.exercise.repository.ExerciseDetailsRepository;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ExerciseDBConfiguration {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private final ExerciseMapper exerciseMapper;
    private final ExerciseDetailsMapper exerciseDetailsMapper;

    @Autowired
    public ExerciseDBConfiguration(
            ExerciseRepository exerciseRepository,
            ExerciseDetailsRepository exerciseDetailsRepository,
            ExerciseMapper exerciseMapper,
            ExerciseDetailsMapper exerciseDetailsMapper
    ){
        this.exerciseRepository = exerciseRepository;
        this.exerciseDetailsRepository = exerciseDetailsRepository;
        this.exerciseMapper = exerciseMapper;
        this.exerciseDetailsMapper = exerciseDetailsMapper;
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
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

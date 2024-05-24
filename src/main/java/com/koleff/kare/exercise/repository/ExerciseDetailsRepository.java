package com.koleff.kare.exercise.repository;

import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseDetailsRepository extends JpaRepository<ExerciseDetails, Long> {
    Optional<ExerciseDetails> findByExerciseDetailsIdAndWorkoutId(Long exerciseDetailsId, Long workoutId);
}
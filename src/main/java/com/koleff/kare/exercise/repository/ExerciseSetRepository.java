package com.koleff.kare.exercise.repository;

import com.koleff.kare.exercise.models.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {
    Optional<ExerciseSet> findById(UUID setId);
    List<ExerciseSet> findByExerciseIdAndWorkoutId(Long exerciseId, Long workoutId);
    void deleteById(UUID setId);
}
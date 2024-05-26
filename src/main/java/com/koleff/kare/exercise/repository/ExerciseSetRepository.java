package com.koleff.kare.exercise.repository;

import com.koleff.kare.exercise.models.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, String> {
    Optional<ExerciseSet> findByExerciseSetId(String exerciseSetId);
    List<ExerciseSet> findByExerciseIdAndWorkoutId(Long exerciseId, Long workoutId);
    void deleteByExerciseSetId(String exerciseSetId);
}
package com.koleff.kare.exercise.repository;

import com.koleff.kare.exercise.models.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseIdAndWorkoutId(Long exerciseId, Long workoutId);
    List<Exercise> findByWorkoutIdAndMuscleGroupId(Long workoutId, Integer muscleGroupId);
    List<Exercise> findByWorkoutId(Long workoutId);
}

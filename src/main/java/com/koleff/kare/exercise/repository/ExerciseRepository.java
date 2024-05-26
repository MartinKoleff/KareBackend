package com.koleff.kare.exercise.repository;

import com.koleff.kare.exercise.models.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseIdAndWorkoutId(Long exerciseId, Long workoutId);
    List<Exercise> findByWorkoutIdAndMuscleGroupId(Long workoutId, Integer muscleGroupId);
    List<Exercise> findByWorkoutId(Long workoutId);

//    @Modifying
//    @Query("UPDATE Exercise e SET e.name = :name, e.muscleGroupId = :muscleGroupId, e.machineTypeId = :machineTypeId, e.snapshot = :snapshot WHERE e.exerciseId = :exerciseId AND e.workoutId = :workoutId")
//    void updateExercise(
//            @Param("name") String name,
//            @Param("muscleGroupId") int muscleGroupId,
//            @Param("machineTypeId") int machineTypeId,
//            @Param("snapshot") String snapshot,
//            @Param("exerciseId") Long exerciseId,
//            @Param("workoutId") Long workoutId
//    );
}

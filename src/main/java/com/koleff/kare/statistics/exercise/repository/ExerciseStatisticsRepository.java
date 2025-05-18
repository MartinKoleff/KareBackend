package com.koleff.kare.statistics.exercise.repository;

import com.koleff.kare.exercise.models.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseStatisticsRepository extends JpaRepository<ExerciseSet, Long> {

    @Query("SELECT MAX(es.weight) FROM ExerciseSet es WHERE es.exerciseId = :exerciseId")
    Float findBestPRByExerciseId(Long exerciseId);

    @Query("SELECT MAX(es.weight) FROM ExerciseSet es WHERE es.reps = 1 AND es.exerciseId = :exerciseId")
    Float find1RepMaxByExerciseId(Long exerciseId);

    @Query("SELECT SUM(es.reps) FROM ExerciseSet es WHERE es.exerciseId = :exerciseId")
    Integer findTotalRepsByExerciseId(Long exerciseId);

    @Query("SELECT COUNT(es) FROM ExerciseSet es WHERE es.exerciseId = :exerciseId")
    Integer countSetsByExerciseId(Long exerciseId);

    @Query("SELECT SUM(es.reps * es.weight) FROM ExerciseSet es WHERE es.exerciseId = :exerciseId")
    Float findTotalWeightLiftedByExerciseId(Long exerciseId);
}

package com.koleff.kare.workout.repository;

import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WorkoutConfigurationRepository extends JpaRepository<WorkoutConfiguration, Long> {

    @Query("SELECT w FROM WorkoutConfiguration w WHERE w.workoutDetailsId = :workoutId")
    WorkoutConfiguration findByWorkoutDetailsId(Long workoutId);

    @Transactional
    @Modifying
    @Query("DELETE FROM WorkoutConfiguration w WHERE w.workoutDetailsId = :workoutId")
    void deleteByWorkoutDetailsId(Long workoutId);
}
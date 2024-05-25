package com.koleff.kare.workout.repository;

import com.koleff.kare.workout.models.entity.WorkoutDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkoutDetailsRepository extends JpaRepository<WorkoutDetails, Long> {

    @Query("SELECT w FROM WorkoutDetails w ORDER BY w.workoutDetailsId")
    List<WorkoutDetails> getWorkoutDetailsOrderedById();

    @Query("SELECT w FROM WorkoutDetails w WHERE w.isFavorite = true")
    List<WorkoutDetails> getWorkoutDetailsByIsFavorite();

    WorkoutDetails getWorkoutDetailsByWorkoutDetailsId(Long workoutId);

    @Transactional
    @Modifying
    @Query("DELETE FROM WorkoutDetails w WHERE w.workoutDetailsId = :workoutId")
    void deleteWorkoutDetails(Long workoutId);

    @Transactional
    @Modifying
    @Query("UPDATE WorkoutDetails w SET w.isFavorite = true WHERE w.workoutDetailsId = :workoutId")
    void favoriteWorkoutDetailsById(Long workoutId);

    @Transactional
    @Modifying
    @Query("UPDATE WorkoutDetails w SET w.isFavorite = false WHERE w.workoutDetailsId = :workoutId")
    void unfavoriteWorkoutDetailsById(Long workoutId);

    @Modifying
    @Query("UPDATE WorkoutDetails w SET w.name = :name, w.description = :description, w.muscleGroupId = :muscleGroupId, w.isFavorite = :isFavorite WHERE w.workoutDetailsId = :workoutDetailsId")
    void updateWorkoutDetails(
            @Param("name") String name,
            @Param("description") String description,
            @Param("muscleGroupId") int muscleGroupId,
            @Param("isFavorite") boolean isFavorite,
            @Param("workoutDetailsId") Long workoutDetailsId
    );
}
package com.koleff.kare.workout.repository;

import com.koleff.kare.workout.models.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT w FROM Workout w ORDER BY w.workoutId")
    List<Workout> getWorkoutsOrderedById();

    @Query("SELECT w FROM Workout w WHERE w.isFavorite = true")
    List<Workout> getWorkoutsByIsFavorite();

    Workout getWorkoutByWorkoutId(Long workoutId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Workout w WHERE w.workoutId = :workoutId")
    void deleteWorkout(Long workoutId);

    @Transactional
    @Modifying
    @Query("UPDATE Workout w SET w.isFavorite = true WHERE w.workoutId = :workoutId")
    void favoriteWorkoutById(Long workoutId);

    @Transactional
    @Modifying
    @Query("UPDATE Workout w SET w.isFavorite = false WHERE w.workoutId = :workoutId")
    void unfavoriteWorkoutById(Long workoutId);
}

//package com.koleff.kare.exercise.models.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ExerciseCompositionId implements Serializable {
//    public static final String EXERCISE_ID_COLUMN = "exercise_id";
//    public static final String WORKOUT_ID_COLUMN = "workout_id";
//
//    @Column(
//            name = EXERCISE_ID_COLUMN,
//            updatable = false,
//            unique = false,
//            nullable = false
//    )
//    @NotNull(message = "Exercise id must not be empty.")
//    private Long exerciseId;
//
//    @Column(
//            name = WORKOUT_ID_COLUMN,
//            updatable = false,
//            unique = false,
//            nullable = false
//    )
//    @NotNull(message = "Workout id must not be empty.")
//    private Long workoutId;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ExerciseCompositionId that = (ExerciseCompositionId) o;
//        return Objects.equals(exerciseId, that.exerciseId) &&
//                Objects.equals(workoutId, that.workoutId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(exerciseId, workoutId);
//    }
//}
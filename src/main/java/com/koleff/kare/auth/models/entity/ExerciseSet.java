package com.koleff.kare.auth.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = ExerciseSet.TABLE_NAME)
@RequiredArgsConstructor
public @Data class ExerciseSet {
    public static final String TABLE_NAME = "exercise_set_table";
    public static final String ID_COLUMN = "set_id";
    public static final String EXERCISE_ID_COLUMN = "exercise_id";
    public static final String WORKOUT_ID_COLUMN = "workout_id";
    public static final String NUMBER_COLUMN = "number";
    public static final String REPS_COLUMN = "reps";
    public static final String WEIGHT_COLUMN = "weight";
    public static final String EXERCISE_ID_FOREIGN_KEY_COLUMN = "exercise_id_fk";

    @Id
    @SequenceGenerator(
            name = "exercise_set_generator",
            sequenceName = "exercise_set_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "exercise_set_generator"
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    private UUID exerciseSetId;

    @Column(
            name = EXERCISE_ID_COLUMN,
            updatable = false,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Exercise id must not be empty.")
    private Integer exerciseId;

    @Column(
            name = WORKOUT_ID_COLUMN,
            updatable = false,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Workout id must not be empty.")
    private Integer workoutId;

    @Column(
            name = NUMBER_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Set number must not be empty.")
    private Integer number; //Sets

    @Column(
            name = REPS_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Reps must not be empty.")
    private Integer reps;

    @Column(
            name = WEIGHT_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Weight must not be empty.")
    private Float weight;

    @ManyToOne
    @JoinColumn(
            name = ID_COLUMN,
            referencedColumnName = Exercise.ID_COLUMN,
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = EXERCISE_ID_FOREIGN_KEY_COLUMN
            )
    )
    private Exercise exercise;
}

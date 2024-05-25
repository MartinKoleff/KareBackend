package com.koleff.kare.workout.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = Workout.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public @Data class Workout {
    public static final String TABLE_NAME = "workout_table";
    public static final String ID_COLUMN = "workout_id";
    public static final String NAME_COLUMN = "name";
    public static final String MUSCLE_GROUP_COLUMN = "muscle_group";
    public static final String SNAPSHOT_COLUMN = "snapshot";
    public static final String TOTAL_EXERCISES_COLUMN = "total_exercises";
    public static final String IS_FAVORITE_COLUMN = "is_favorite";

    @Id
    @SequenceGenerator(
            name = "workout_generator",
            sequenceName = "workout_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "workout_generator"
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Workout id must not be empty")
    private Long workoutId;

    @Column(
            name = NAME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    private String name;

    @Column(
            name = MUSCLE_GROUP_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Muscle group id must not be empty")
    private Integer muscleGroupId;

    @Column(
            name = SNAPSHOT_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Snapshot must not be empty")
    private String snapshot;

    @Column(
            name = TOTAL_EXERCISES_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Total exercises must not be empty")
    private Integer totalExercises;

    @Column(
            name = IS_FAVORITE_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Is favorite must not be empty")
    private Boolean isFavorite;

    @OneToMany(
            mappedBy = "workout",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutDetails> workoutDetails;
}

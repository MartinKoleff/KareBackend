package com.koleff.kare.workout.models.entity;

import com.koleff.kare.exercise.models.entity.Exercise;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = WorkoutDetails.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public @Data class WorkoutDetails {
    public static final String TABLE_NAME = "workout_details_table";
    public static final String ID_COLUMN = "workout_details_id";
    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String MUSCLE_GROUP_COLUMN = "muscle_group";
    public static final String IS_FAVORITE_COLUMN = "is_favorite";
    public static final String WORKOUT_ID_FOREIGN_KEY_COLUMN = "workout_id_fk";

    @Id
    @SequenceGenerator(
            name = "workout_details_generator",
            sequenceName = "workout_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "workout_details_generator"
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Workout details id must not be empty")
    private Long workoutDetailsId;

    @Column(
            name = NAME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Name must not be empty")
    private String name;

    @Column(
            name = DESCRIPTION_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Description must not be empty")
    private String description;

    @Column(
            name = MUSCLE_GROUP_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Muscle group id must not be empty")
    private Integer muscleGroupId;

    @Column(
            name = IS_FAVORITE_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Is favorite must not be empty")
    private Boolean isFavorite;

    @OneToOne
    @JoinColumn(
            name = ID_COLUMN,
            referencedColumnName = Workout.ID_COLUMN,
            nullable = false,
            insertable = false,
            updatable = false
//            foreignKey = @ForeignKey(
//                    name = WORKOUT_ID_FOREIGN_KEY_COLUMN
//            )
    )
    private Workout workout;

    @OneToMany(
            mappedBy = "workoutDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Exercise> exercises;

    @OneToOne(
            mappedBy = "workoutDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private WorkoutConfiguration configuration;
}

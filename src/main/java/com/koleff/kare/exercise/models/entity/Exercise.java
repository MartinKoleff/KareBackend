package com.koleff.kare.exercise.models.entity;

import com.koleff.kare.workout.models.entity.WorkoutDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koleff.kare.auth.models.entity.User.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@RequiredArgsConstructor
public @Data class Exercise {
    public static final String TABLE_NAME = "exercise_table";
    public static final String ID_COLUMN = "exercise_id";
    public static final String WORKOUT_DETAILS_ID_FOREIGN_KEY_COLUMN = "workout_details_id_fk";
    public static final String NAME_COLUMN = "name";
    public static final String MUSCLE_GROUP_ID_COLUMN = "muscle_group_id_column";
    public static final String MACHINE_TYPE_ID_COLUMN = "machine_type_id_column";
    public static final String SNAPSHOT_COLUMN = "snapshot_column";

    @Id
    @SequenceGenerator(
            name = "exercise_generator",
            sequenceName = "exercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "exercise_generator"
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    private Long exerciseId;

    @Column(
            name = NAME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Name must not be empty.")
    private String name;

    @Column(
            name = MUSCLE_GROUP_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Muscle group id must not be empty.")
    private Integer muscleGroupId;

    @Column(
            name = MACHINE_TYPE_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Machine type id must not be empty.")
    private Integer machineTypeId;

    @Column(
            name = SNAPSHOT_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Snapshot must not be empty.")
    private String snapshot;

    @ManyToOne
    @JoinColumn(
            name = ID_COLUMN,
            referencedColumnName = WorkoutDetails.ID_COLUMN,
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = WORKOUT_DETAILS_ID_FOREIGN_KEY_COLUMN
            )
    )
    private WorkoutDetails workoutDetails;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> sets;
}
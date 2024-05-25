package com.koleff.kare.exercise.models.entity;

import com.koleff.kare.workout.models.entity.WorkoutDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.koleff.kare.auth.models.entity.User.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public @Data class ExerciseDetails {
    public static final String TABLE_NAME = "exercise_details_table";
    public static final String ID_COLUMN = "exercise_details_id";
    public static final String WORKOUT_ID_COLUMN = "workout_id";
    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String MUSCLE_GROUP_ID_COLUMN = "muscle_group_id_column";
    public static final String MACHINE_TYPE_ID_COLUMN = "machine_type_id_column";
    public static final String SNAPSHOT_COLUMN = "snapshot_column";
    public static final String VIDEO_URL_COLUMN = "video_url_column";
    public static final String EXERCISE_ID_FOREIGN_KEY_COLUMN = "exercise_id_fk";

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "exercise_generator" //Use same generator as exercise -> same IDs
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    private Long exerciseDetailsId;

    @Column(
            name = WORKOUT_ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Workout id must not be empty.")
    private Long workoutId;

    @Column(
            name = NAME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Name must not be empty.")
    private String name;

    @Column(
            name = DESCRIPTION_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Description must not be empty.")
    private String description;

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

    @Column(
            name = VIDEO_URL_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Video url must not be empty.")
    private String videoUrl;

    @OneToOne
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
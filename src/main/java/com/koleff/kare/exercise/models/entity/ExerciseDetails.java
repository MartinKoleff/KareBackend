package com.koleff.kare.exercise.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = ExerciseDetails.TABLE_NAME)
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

    public ExerciseDetails(Long exerciseDetailsId, Long workoutId, String name, String description, Integer muscleGroupId, Integer machineTypeId, String snapshot, String videoUrl) {
        this.exerciseDetailsId = exerciseDetailsId;
        this.workoutId = workoutId;
        this.name = name;
        this.description = description;
        this.muscleGroupId = muscleGroupId;
        this.machineTypeId = machineTypeId;
        this.snapshot = snapshot;
        this.videoUrl = videoUrl;
    }

    @Id
    @SequenceGenerator(
            name = "exercise_details_generator",
            sequenceName = "exercise_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "exercise_details_generator" //TODO: Use same generator as exercise -> same IDs
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
            unique = false,
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
    @Size(max = 500)
    @NotNull(message = "Name must not be empty.")
    private String name;

    @Column(
            name = DESCRIPTION_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @Size(max = 2000)
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
package com.koleff.kare.workout.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = WorkoutConfiguration.TABLE_NAME)
@RequiredArgsConstructor
public @Data class WorkoutConfiguration {
    public static final String TABLE_NAME = "workout_configuration_table";
    public static final String ID_COLUMN = "workout_configuration_id";
    public static final String WORKOUT_DETAILS_ID_COLUMN = "workout_details_id";
    public static final String COOLDOWN_TIME_COLUMN = "cooldown_time";

    @Id
    @SequenceGenerator(
            name = "workout_configuration_generator",
            sequenceName = "workout_configuration_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "workout_configuration_generator"
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Workout configuration id must not be empty")
    private Long workoutConfigurationId;

    @Column(
            name = WORKOUT_DETAILS_ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Workout details id must not be empty")
    private Long workoutDetailsId;

    @Column(
            name = COOLDOWN_TIME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Cooldown time must not be empty")
    private String cooldownTime;

    @OneToOne
    @JoinColumn(name = WORKOUT_DETAILS_ID_COLUMN, nullable = false, insertable = false, updatable = false)
    private WorkoutDetails workoutDetails;
}

package com.koleff.kare.do_workout_performance_metrics.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.koleff.kare.workout.models.entity.WorkoutConfiguration.WORKOUT_DETAILS_ID_COLUMN;

@Entity
@Table(name = DoWorkoutExerciseSet.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public @Data class DoWorkoutExerciseSet {
    public static final String TABLE_NAME = "do_workout_exercise_set";
    public static final String ID_COLUMN = "id";
    public static final String DO_WORKOUT_PERFORMANCE_METRICS_ID_COLUMN = "do_workout_performance_metrics_id";
    public static final String WORKOUT_ID_COLUMN = "workout_id";
    public static final String EXERCISE_ID_COLUMN = "exercise_id";
    public static final String TEMPLATE_SET_ID_COLUMN = "template_set_id";
    public static final String REPS_COLUMN = "reps";
    public static final String WEIGHT_COLUMN = "weight";
    public static final String IS_DONE_COLUMN = "is_done";
    public static final String TIME_COLUMN = "time";
    public static final String DATE_COLUMN = "date";
    public static final String DO_WORKOUT_PERFORMANCE_METRICS_FOREIGN_KEY_COLUMN = "do_workout_performance_metrics_fk";

    @Id
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Do workout exercise set instance id must not be empty")
    private UUID instanceId;

    @Column(
            name = DO_WORKOUT_PERFORMANCE_METRICS_ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Do workout performance metrics id must not be empty")
    private Long doWorkoutPerformanceMetricsId;

    @Column(
            name = WORKOUT_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Workout id must not be empty")
    private Long workoutId;

    @Column(
            name = EXERCISE_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Exercise id must not be empty")
    private Long exerciseId;

    @Column(
            name = TEMPLATE_SET_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Template set id must not be empty")
    private UUID templateSetId;

    @Column(
            name = REPS_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Reps must not be empty")
    private Integer reps;

    @Column(
            name = WEIGHT_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Weight must not be empty")
    private Float weight;

    @Column(
            name = IS_DONE_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Is done must not be empty")
    private Boolean isDone;

    @Column(
            name = TIME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Time done must not be empty")
    @Embedded
    private ExerciseTime time;

    @Column(
            name = DATE_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Date done must not be empty")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(
            name = DO_WORKOUT_PERFORMANCE_METRICS_ID_COLUMN,
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = DO_WORKOUT_PERFORMANCE_METRICS_FOREIGN_KEY_COLUMN
            )
    )
    private DoWorkoutPerformanceMetrics doWorkoutPerformanceMetrics;
}
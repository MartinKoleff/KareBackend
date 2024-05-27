package com.koleff.kare.do_workout_performance_metrics.models.entity;

import com.koleff.kare.workout.models.entity.Workout;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = DoWorkoutPerformanceMetrics.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public @Data class DoWorkoutPerformanceMetrics {
    public static final String TABLE_NAME = "do_workout_performance_metrics";
    public static final String ID_COLUMN = "id";
    public static final String WORKOUT_ID_COLUMN = "workout_id";
    public static final String DATE_COLUMN = "date";

    @Id
    @SequenceGenerator(
            name = "do_workout_performance_metrics_generator",
            sequenceName = "do_workout_performance_metrics_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "do_workout_performance_metrics_generator"
    )
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Do workout performance metrics id must not be empty")
    private Long id; //TODO: migrate to UUID in future...

    @Column(
            name = WORKOUT_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Workout id must not be empty")
    private Long workoutId;

    @Column(
            name = DATE_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Date must not be empty")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(
            mappedBy = "doWorkoutPerformanceMetrics",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DoWorkoutExerciseSet> doWorkoutExerciseSets;
}
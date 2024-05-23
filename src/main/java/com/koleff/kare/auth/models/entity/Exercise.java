package com.koleff.kare.auth.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import static com.koleff.kare.auth.models.entity.User.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@RequiredArgsConstructor
public @Data class Exercise {
    public static final String TABLE_NAME = "exercise_table";
    public static final String ID_COLUMN = "exercise_id";
    public static final String NAME_COLUMN = "name";
    public static final String MUSCLE_GROUP_ID_COLUMN = "muscle_group_id_column";
    public static final String MACHINE_TYPE_ID_COLUMN = "machine_type_id_column";
    public static final String SNAPSHOT_COLUMN = "snapshot_column";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    private Long id;

    @Column(
            name = NAME_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    private String name;

    @Column(
            name = MUSCLE_GROUP_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    private Integer muscleGroupId;

    @Column(
            name = MACHINE_TYPE_ID_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    private Integer machineTypeId;

    @Column(
            name = SNAPSHOT_COLUMN,
            updatable = true,
            unique = false,
            nullable = false
    )
    private String snapshot;

//    @ManyToOne
//    @JoinColumn(name = "workout_details_id", nullable = false)
//    private WorkoutDetails workoutDetails;
//
//    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ExerciseSet> sets;
//
//    // Getters and Setters
}
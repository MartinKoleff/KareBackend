package com.koleff.kare.auth.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.koleff.kare.auth.models.entity.Permission.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    public static final String TABLE_NAME = "permission_table";
    public static final String ID_COLUMN = "permission_id";
    public static final String PERMISSION_COLUMN = "permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Permission id must not be empty.")
    private Long id;

    @Column(
            name = PERMISSION_COLUMN,
            unique = true,
            nullable = false,
            updatable = false
    )
    @NotNull(message = "Permission must not be empty.")
    private String permission; //name
}
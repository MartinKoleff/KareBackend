package com.koleff.kare.models.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static com.koleff.kare.models.entity.Role.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class Role implements GrantedAuthority {

    public static final String TABLE_NAME = "role_table";
    public static final String ID_COLUMN = "role_id";
    public static final String AUTHORITY_COLUMN = "authority";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = ID_COLUMN,
            nullable = false,
            unique = true,
            updatable = false
    )
    @NotNull(message = "Role id must not be empty.")
    private Integer roleId;

    @Column(name = AUTHORITY_COLUMN,
            nullable = false,
            unique = false,
            updatable = true
    )
    @NotNull(message = "Authority must not be empty.")
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }
}

//TODO: migrate roles to enums with permission lists...

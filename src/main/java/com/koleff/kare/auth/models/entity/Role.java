package com.koleff.kare.auth.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static com.koleff.kare.auth.models.entity.Role.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    public static final String TABLE_NAME = "roles_table";
    public static final String ID_COLUMN = "role_id";
    public static final String AUTHORITY_COLUMN = "authority";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Role id must not be empty.")
    private Long id;

    @Column(
            name = AUTHORITY_COLUMN,
            unique = true,
            nullable = false,
            updatable = false
    )
    @NotNull(message = "Authority must not be empty.")
    private String authority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions_junction",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

//    @Override
//    public String getAuthority() {
//        return "ROLE_" + this.authority.toUpperCase();
//    }
}
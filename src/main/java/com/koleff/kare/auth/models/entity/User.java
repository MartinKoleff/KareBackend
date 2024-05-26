package com.koleff.kare.auth.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.koleff.kare.auth.models.entity.User.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@AllArgsConstructor
public @Data class User implements UserDetails { //TODO: Add Jackson serialization / deserialization...

    public static final String TABLE_NAME = "user_table";
    public static final String ID_COLUMN = "user_id";
    public static final String EMAIL_COLUMN = "email";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";

    @Id
    @UuidGenerator
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "User id must not be empty.")
    private String id;

    @Column(
            name = USERNAME_COLUMN,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Username must not be empty.")
    private String username;

    @Column(
            name = PASSWORD_COLUMN,
            unique = false,
            nullable = false
    )
    @NotNull(message = "Username must not be empty.")
    private String password;

    @Column(
            name = EMAIL_COLUMN,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Email must not be empty.")
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = @JoinColumn(name = ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = Role.ID_COLUMN)
    )
    private Set<Role> authorities;

    //Creating new user with no roles
    public User() {
        super();
        authorities = new HashSet<>();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(this.authorities);
    }

    /**
     * If you want account locking capabilities create variables and ways to set them for the methods below
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

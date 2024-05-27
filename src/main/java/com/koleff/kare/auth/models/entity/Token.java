package com.koleff.kare.auth.models.entity;

import com.koleff.kare.workout.models.entity.Workout;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = Token.TABLE_NAME)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class Token {

    public static final String TABLE_NAME = "token_table";
    public static final String ID_COLUMN = "token_id";
    public static final String TOKEN_COLUMN = "token";
    public static final String TOKEN_TYPE_COLUMN = "token_type";
    public static final String REVOKED_COLUMN = "revoked";
    public static final String EXPIRED_COLUMN = "expired";
    public static final String EXPIRY_TIME_COLUMN = "expiry_time";
    public static final String IS_REFRESH_TOKEN_COLUMN = "is_refresh_token";
    public static final String USER_FOREIGN_KEY_COLUMN = "user_fk";
    public static final String USER_ID_COLUMN = "user_id";

    @Id
    @GeneratedValue
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Token id must not be empty.")
    public Long id;

    @Column(
            name = USER_ID_COLUMN,
            updatable = false,
            unique = false,
            nullable = false
    )
    @NotNull(message = "User id must not be empty.")
    public String userId;

    @Column(
            name = TOKEN_COLUMN,
            unique = true,
            nullable = true,
            updatable = true,
            length = 1024
    )
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(
            name = TOKEN_TYPE_COLUMN,
            unique = false,
            updatable = false,
            nullable = false
    )
    @NotNull(message = "Token type must not be empty.")
    public TokenType tokenType = TokenType.BEARER;

    @Column(
            name = REVOKED_COLUMN,
            unique = false,
            updatable = true,
            nullable = false
    )
    @NotNull(message = "Revoked must not be empty.")
    public boolean revoked;

    @Column(
            name = EXPIRED_COLUMN,
            unique = false,
            updatable = true,
            nullable = false
    )
    @NotNull(message = "Expired must not be empty.")
    public boolean expired;

    @Column(
            name = IS_REFRESH_TOKEN_COLUMN,
            unique = false,
            updatable = false,
            nullable = false
    )
    @NotNull(message = "Is refresh token must not be empty.")
    public boolean isRefreshToken;

    @Column(
            name = EXPIRY_TIME_COLUMN,
            nullable = false,
            unique = false,
            updatable = true
    )
    @NotNull(message = "Expiry time must not be empty.")
    public Instant expiryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = USER_ID_COLUMN,
            referencedColumnName = User.ID_COLUMN,
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = USER_FOREIGN_KEY_COLUMN
            )
    )
    public User user;
}

package com.koleff.kare.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static com.koleff.kare.models.entity.Token.TABLE_NAME;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = TABLE_NAME)
public class Token {

    public static final String TABLE_NAME = "token_table";
    public static final String ID_COLUMN = "id";
    public static final String TOKEN_COLUMN = "token";
    public static final String TOKEN_TYPE_COLUMN = "token_type";
    public static final String REVOKED_COLUMN = "revoked";
    public static final String EXPIRED_COLUMN = "expired";

    @Id
    @GeneratedValue
    @Column(
            name = ID_COLUMN,
            updatable = false,
            unique = true,
            nullable = false
    )
    @NotNull(message = "Token id must not be empty.")
    public Integer id;

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

  public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}

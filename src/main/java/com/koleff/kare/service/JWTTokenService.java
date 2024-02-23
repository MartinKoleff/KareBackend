package com.koleff.kare.service;

import com.koleff.kare.models.entity.Token;
import com.koleff.kare.models.entity.TokenType;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JWTTokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;
    private final TokenRepository tokenRepository;

    @Autowired
    public JWTTokenService(JwtEncoder jwtEncoder,
                           JwtDecoder jwtDecoder,
                           @Value("${jwt.access-token.expiration-time}") long accessTokenExpirationTime,
                           @Value("${jwt.refresh-token.expiration-time}") long refreshTokenExpirationTime,
                           TokenRepository tokenRepository) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.tokenRepository = tokenRepository;
    }

    public String generateAccessToken(User user) {
        JwtClaimsSet claims = buildClaimsSet(user.getId(), accessTokenExpirationTime, user);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(User user) {
        JwtClaimsSet claims = buildClaimsSet(user.getId(), refreshTokenExpirationTime, null);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private JwtClaimsSet buildClaimsSet(String subject, long expirationTimeMillis, User user) {
        Instant now = Instant.now();
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);

        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(subject)
                .expiresAt(expirationDate.toInstant());

        //Only for access token
        if (user != null) {
            String roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            claimsBuilder.claim("roles", roles);
            claimsBuilder.claim("username", user.getUsername());
//            claimsBuilder.claim("email", user.getEmail());
        }

        return claimsBuilder.build();
    }
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Validation
     */

    public String extractUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaim("username");
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return Objects.requireNonNull(jwt.getExpiresAt()).isBefore(Instant.now());
    }
}



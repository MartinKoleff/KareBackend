package com.koleff.kare.service;

import com.koleff.kare.models.entity.Role;
import com.koleff.kare.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JWTTokenService{

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final long expirationTime;

    @Autowired
    public JWTTokenService(JwtEncoder jwtEncoder,
                           JwtDecoder jwtDecoder,
                           @Value("${jwt.expiration-time}") long expirationTime) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.expirationTime = expirationTime;
    }

    public String generateToken(Authentication auth) {

        //Creation
        Instant now = Instant.now();

        //Expiration
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + this.expirationTime);

        //Authorities
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") //by this backend server
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .expiresAt(expirationDate.toInstant()) //expiration
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

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



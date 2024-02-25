package com.koleff.kare.auth.service;

import com.koleff.kare.auth.models.entity.User;
import com.koleff.kare.auth.models.entity.Token;
import com.koleff.kare.auth.models.entity.TokenType;
import com.koleff.kare.auth.repository.TokenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JWTTokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;
    private final TokenRepository tokenRepository;
    private final static Logger logger = LogManager.getLogger(JWTTokenService.class);


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

        return generateToken(user, claims, false);
    }

    public String generateRefreshToken(User user) {
        JwtClaimsSet claims = buildClaimsSet(user.getId(), refreshTokenExpirationTime, user);

        return generateToken(user, claims, true);
    }

    private String generateToken(User user, JwtClaimsSet claims, Boolean isRefreshToken) {
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        saveToken(user, token, isRefreshToken);

        return token;
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
            claimsBuilder.claim("email", user.getEmail());
        }

        return claimsBuilder.build();
    }


    /**
     * Token functionalities
     */
    private void saveToken(User user, String jwtToken, Boolean isRefreshToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .isRefreshToken(isRefreshToken)
                .expiryTime(Instant.now().plus(accessTokenExpirationTime / 1000, ChronoUnit.SECONDS))
                .build();
        Token savedToken = tokenRepository.save(token);
        logger.info(String.format("Token saved in DB: %s", savedToken));
    }

    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    /**
     * Validation
     */

    public String extractUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaim("username");
    }

    public String extractEmail(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaim("email");
    }

    public String extractUserId(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject(); //sub
    }

    public boolean validateToken(String token, UserDetails userDetails) throws NullPointerException {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) throws NullPointerException {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getExpiresAt().isBefore(Instant.now());
    }
}



package com.koleff.kare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JWTTokenService implements TokenService {

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

    @Override
    public Token allocateToken(String extendedInformation) {
        return null;
    }

    @Override
    public Token verifyToken(String key) {
        return null;
    }
}

//-----------Idea-----------
//	public String generateToken(UserEntity userEntity) {
//		HashMap<String, Object> claims = new HashMap<>();
//		claims.put("email", userEntity.getEmail());
//		claims.put("username", userEntity.getUsername());
//		claims.put("role", "USER");
//		return this.jwtGenerator.generateToken(userEntity.getId(), claims);
//	}

//public String generateToken(String subjectId, Map<String, Object> claims) {
//		Date creationDate = new Date();
//		Date expirationDate = new Date(creationDate.getTime() + this.expirationTime);
//
//		Map<String, Object> actualClaims = new HashMap<>(claims);
//		actualClaims.put(SUBJECT_ID_CLAIMS_NAME, subjectId);
//
//		return Jwts.builder()
//			.setClaims(actualClaims)
//			.setIssuedAt(creationDate)
//			.setExpiration(expirationDate)
//			.signWith(Keys.hmacShaKeyFor(this.secretKey.getBytes()))
//			.compact();
//	}
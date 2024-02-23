package com.koleff.kare.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.koleff.kare.models.dto.AuthenticationResponse;
import com.koleff.kare.models.entity.Role;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.repository.RoleRepository;
import com.koleff.kare.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenService jwtTokenService;
    private final static Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JWTTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public User registerUser(String username, String password, String email) { //TODO: add access and refresh token generation directly instead of having to login...

        //Encode the password
        String encodedPassword = passwordEncoder.encode(password);

        //Give USER role to user.
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        //Create user and save in DB.
        User user = new User(UUID.randomUUID().toString(), username, encodedPassword, email, authorities);
        logger.info(String.format("User created! User: %s", user));

        return userRepository.save(user);
    }

    @Override
    public AuthenticationResponse loginUser(String username, String password) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            //Check for valid user in the DB
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String accessToken = jwtTokenService.generateAccessToken(user);
            logger.info(String.format("Access token generated: %s", accessToken));

            String refreshToken = jwtTokenService.generateRefreshToken(user);
            logger.info(String.format("Refresh token generated: %s", refreshToken));

            return AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .user(user)
                    .build();
        } catch (AuthenticationException e) {

            return AuthenticationResponse.builder()
                    .accessToken("")
                    .refreshToken("")
                    .user(null)
                    .build();
        }
    }

    @Override
    public AuthenticationResponse refreshToken(String refreshToken) {

        //Find user from the token
        String userId = jwtTokenService.extractUserId(refreshToken);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //Validate refresh token
        if (jwtTokenService.validateToken(refreshToken, user)) {

            //Generate new tokens
            String newAccessToken = jwtTokenService.generateAccessToken(user);
            logger.info(String.format("New access token generated: %s", newAccessToken));

            String newRefreshToken = jwtTokenService.generateRefreshToken(user);
            logger.info(String.format("New refresh token generated: %s", newRefreshToken));

            return AuthenticationResponse.builder()
                    .refreshToken(newRefreshToken)
                    .accessToken(newAccessToken)
                    .build();
        }

        //Invalid token
        return AuthenticationResponse.builder()
                .refreshToken("")
                .accessToken("")
                .build();
    }
}

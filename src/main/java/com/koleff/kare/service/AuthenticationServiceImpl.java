package com.koleff.kare.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koleff.kare.models.dto.AuthenticationResponse;
import com.koleff.kare.models.entity.Role;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.repository.RoleRepository;
import com.koleff.kare.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
            jwtTokenService.saveAccessToken(user, accessToken); //only save the access token

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

    //TODO: write more modern way...
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String username;
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//
//        refreshToken = authHeader.substring(7);
//        username = jwtTokenService.extractUsername(refreshToken);
//
//        if (username != null) {
//            var user = userRepository.findByUsername(username)
//                    .orElseThrow();
//
//            if (jwtTokenService.validateToken(refreshToken, user)) {
//
//                var accessToken = jwtTokenService.generateAccessToken(user);
//                jwtTokenService.revokeAllUserTokens(user);
//                jwtTokenService.saveAccessToken(user, accessToken);
//
//                var authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//
//                //Update response with new one
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
}

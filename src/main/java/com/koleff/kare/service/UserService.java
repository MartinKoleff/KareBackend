package com.koleff.kare.service;

import com.koleff.kare.models.entity.Role;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final static Logger logger = LogManager.getLogger(UserService.class);

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("In User Service!");

        if(!username.equals("Koleff")) throw new UsernameNotFoundException("Username Koleff not found");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "USER"));

        return new User(
                UUID.randomUUID().toString(),
                "Koleff",
                passwordEncoder.encode("epic_password_69"),
                "koleff@aitos.com",
                roles
        );
    }
}

package com.koleff.kare.configuration;

import com.koleff.kare.models.entity.Role;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.repository.RoleRepository;
import com.koleff.kare.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
public class ApplicationConfiguration {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationConfiguration(RoleRepository roleRepository,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Always add Admin user and all roles in the DB
     */
    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {

            //DB is initialized
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(UUID.randomUUID().toString(),
                    "admin",
                    passwordEncoder.encode("password"),
                    "admin@admin.com",
                    roles);

            userRepository.save(admin);
        };
    }
}

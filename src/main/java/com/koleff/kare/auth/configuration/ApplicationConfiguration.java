package com.koleff.kare.auth.configuration;

import com.koleff.kare.auth.models.entity.Permission;
import com.koleff.kare.auth.models.entity.Role;
import com.koleff.kare.auth.models.entity.User;
import com.koleff.kare.auth.repository.PermissionRepository;
import com.koleff.kare.auth.repository.RoleRepository;
import com.koleff.kare.auth.repository.UserRepository;
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
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationConfiguration(RoleRepository roleRepository,
                                    PermissionRepository permissionRepository,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Always add Admin user and all roles and permissions in the DB
     */
    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {

            //DB is initialized
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            //Create permissions
            Permission createWorkout = permissionRepository.save(new Permission(1L, "CREATE_WORKOUT"));
            Permission deleteWorkout = permissionRepository.save(new Permission(2L, "DELETE_WORKOUT"));
            Permission fetchWorkouts = permissionRepository.save(new Permission(3L, "FETCH_WORKOUTS"));
            Permission selectWorkout = permissionRepository.save(new Permission(4L, "SELECT_WORKOUT"));
            Permission renameWorkout = permissionRepository.save(new Permission(5L, "RENAME_WORKOUT"));
            Permission addExercise = permissionRepository.save(new Permission(6L, "ADD_EXERCISE"));
            Permission deleteExercise = permissionRepository.save(new Permission(7L, "DELETE_EXERCISE"));

            //TODO: update permissions in the future...
            Set<Permission> adminPermissions = Set.of(createWorkout, deleteWorkout, fetchWorkouts, selectWorkout, renameWorkout, addExercise, deleteExercise);
            Set<Permission> userPermissions = Set.of(createWorkout, deleteWorkout, fetchWorkouts, selectWorkout, renameWorkout, addExercise, deleteExercise);
            Set<Permission> coachPermissions = Set.of(createWorkout, deleteWorkout, fetchWorkouts, selectWorkout, renameWorkout, addExercise, deleteExercise);

            //Create roles
            Role adminRole = new Role(
                    1L, "ADMIN",
                    new HashSet<>(adminPermissions)
            );

            Role userRole = new Role(
                    2L, "USER",
                    new HashSet<>(userPermissions)
            );

            Role coachRole = new Role(
                    3L, "COACH",
                    new HashSet<>(coachPermissions)
            );

            //Save to DB
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            roleRepository.save(coachRole);

            //Create admin user
            User admin = new User(UUID.randomUUID().toString(),
                    "admin",
                    passwordEncoder.encode("password"),
                    "admin@test.com",
                    Set.of(
                            adminRole,
                            userRole,
                            coachRole
                    ));

            userRepository.save(admin);
        };
    }
}

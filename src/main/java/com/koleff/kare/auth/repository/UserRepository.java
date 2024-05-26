package com.koleff.kare.auth.repository;

import com.koleff.kare.auth.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
}

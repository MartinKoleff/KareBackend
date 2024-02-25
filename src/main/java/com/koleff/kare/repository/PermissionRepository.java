package com.koleff.kare.repository;

import com.koleff.kare.models.entity.Permission;
import com.koleff.kare.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByPermission(String permission);

}

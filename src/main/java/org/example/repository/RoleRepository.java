package org.example.repository;

import org.example.enums.RoleName;
import org.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleName roleName);
}

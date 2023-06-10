package com.ragalzi.project.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.project.security.enumerates.UserRole;
import com.ragalzi.project.security.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRole(UserRole role);

	Boolean existsByRole(UserRole role);

}

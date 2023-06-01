package com.ragalzi.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.project.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

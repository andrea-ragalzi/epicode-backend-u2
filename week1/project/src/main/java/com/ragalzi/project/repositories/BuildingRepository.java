package com.ragalzi.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.project.models.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByName(String building);
}

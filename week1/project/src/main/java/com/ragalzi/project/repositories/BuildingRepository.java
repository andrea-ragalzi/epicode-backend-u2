package com.ragalzi.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.project.models.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByName(String name);

}

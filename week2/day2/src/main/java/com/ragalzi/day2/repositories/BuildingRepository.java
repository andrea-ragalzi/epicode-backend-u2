package com.ragalzi.day2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.day2.models.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByName(String name);

}

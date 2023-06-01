package com.ragalzi.project.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ragalzi.project.models.Building;
import com.ragalzi.project.repositories.BuildingRepository;

@Component
@Order(2)
public class BuildingRunner implements CommandLineRunner {
    @Autowired
    private BuildingRepository buildingRepository;

    public static final int QUANTITY = 20;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < BuildingRunner.QUANTITY; i++) {
            Building building = Building.randomBuilding();
            buildingRepository.save(building);
        }

    }
}
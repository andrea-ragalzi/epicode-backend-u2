package com.ragalzi.day2.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ragalzi.day2.models.Building;
import com.ragalzi.day2.services.BuildingService;

@Component
@Order(2)
public class BuildingRunner implements CommandLineRunner {
    @Autowired
    private BuildingService buildingService;

    @Value("${buildings_quantity}")
    private int quantity;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < this.quantity; i++) {
            this.saveBuildingFake();
        }
    }

    private void saveBuildingFake() {
        Building building = this.buildingService.createBuildingFake();
        this.buildingService.save(building);
    }
}
package com.ragalzi.project.runners;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.ragalzi.project.enumerates.WorkspaceType;
import com.ragalzi.project.models.Building;
import com.ragalzi.project.models.Workspace;
import com.ragalzi.project.repositories.BuildingRepository;
import com.ragalzi.project.repositories.WorkspaceRepository;

@Component
@Order(3)
public class WorkspaceRunner implements CommandLineRunner {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public static final int QUANTITY = 200;

    @Override
    public void run(String... args) throws Exception {
        List<Building> buildings = buildingRepository.findAll();
        Faker faker = new Faker(new Locale("it"));
        Random rand = new Random();
        WorkspaceType[] types = WorkspaceType.values();

        for (int i = 0; i < WorkspaceRunner.QUANTITY; i++) {
            Workspace workspace = new Workspace();
            workspace.setName(faker.pokemon().name());
            workspace.setDescription(faker.lorem().sentence());
            workspace.setType(types[rand.nextInt(types.length)]);
            workspace.setMaxOccupancy(
                    faker.number().numberBetween(1, 100));
            workspace.setBuilding(buildings.get(i % buildings.size()));
            workspaceRepository.save(workspace);
        }
    }

}

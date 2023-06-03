package com.ragalzi.project.runners;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ragalzi.project.models.Building;
import com.ragalzi.project.models.Workspace;
import com.ragalzi.project.services.BuildingService;
import com.ragalzi.project.services.WorkspaceService;

@Component
@Order(3)
public class WorkspaceRunner implements CommandLineRunner {
    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private BuildingService buildingService;

    @Value("${workspaces_quantity}")
    private int quantity;

    @Override
    public void run(String... args) throws Exception {
        List<Building> buildings = this.buildingService.findAll();
        for (int i = 0; i < this.quantity; i++) {
            Building building = buildings.get(
                    new Random().nextInt(buildings.size()));
            this.saveWorkspaceFake(building);
        }
    }

    private void saveWorkspaceFake(Building building) {
        Workspace workspace;
        do {
            workspace = this.workspaceService.createWorkspaceFake();
            workspace.setBuilding(building);
        } while (this.workspaceService.getByNameAndBuilding(
                workspace.getName(), building) != null);
        this.workspaceService.save(workspace);
    }

}

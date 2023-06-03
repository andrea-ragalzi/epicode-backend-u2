package com.ragalzi.project.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ragalzi.project.enumerates.WorkspaceType;
import com.ragalzi.project.models.Building;
import com.ragalzi.project.models.Reservation;
import com.ragalzi.project.models.Workspace;
import com.ragalzi.project.repositories.WorkspaceRepository;

@Service
public class WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    @Qualifier("WorkspaceNoParamsBean")
    ObjectProvider<Workspace> workspaceNoParamsProvider;

    @Autowired
    @Qualifier("WorkspaceWithParamsBean")
    ObjectProvider<Workspace> workspaceWithParamsProvider;

    @Autowired
    @Qualifier("WorkspaceFakeBean")
    ObjectProvider<Workspace> workspaceFakeProvider;

    public Workspace createWorkspaceNoParamsWorkspace() {
        return workspaceNoParamsProvider.getObject();
    }

    public Workspace createWorkspaceWithParamsWorkspace(
            String name, String description,
            WorkspaceType type, int maxOccupancy) {
        return workspaceWithParamsProvider.getObject();
    }

    public Workspace createWorkspaceFake() {
        return workspaceFakeProvider.getObject();
    }

    public boolean isWorkspaceAvailable(
            Workspace workspace, Reservation newReservation) {
        for (Reservation reservation : workspace.getReservations()) {
            if (reservation.getDate().isEqual(newReservation.getDate())) {
                if (checkScheduleOverlap(
                        newReservation.getStartTime(),
                        newReservation.getEndTime(),
                        reservation.getStartTime(),
                        reservation.getEndTime()))
                    return false;
            }
        }
        return true;
    }

    public void save(Workspace workspace) {
        workspaceRepository.save(workspace);
    }

    public void update(Workspace workspace) {
        workspaceRepository.save(workspace);
    }

    public void delete(Workspace workspace) {
        workspaceRepository.delete(workspace);
    }

    public Workspace getById(Long id) {
        return workspaceRepository.findById(id).get();
    }

    public List<Workspace> getByName(String name) {
        return workspaceRepository.findByName(name);
    }

    public List<Workspace> getAll() {
        return workspaceRepository.findAll();
    }

    public Workspace getByNameAndBuilding(String name, Building building) {
        return workspaceRepository.findByNameAndBuilding(name, building);
    }

    public List<Workspace> findByTypeAndCity(WorkspaceType type, String city) {
        return workspaceRepository.findByTypeAndCity(type, city);
    }

    private boolean checkScheduleOverlap(
            LocalTime startTime1, LocalTime endTime1,
            LocalTime startTime2, LocalTime endTime2) {
        return !endTime1.isBefore(startTime2) && !endTime2.isBefore(startTime1);
    }

}
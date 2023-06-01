package com.ragalzi.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.project.models.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    Workspace findByName(String workspace);
}

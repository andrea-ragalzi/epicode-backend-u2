package com.ragalzi.day2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ragalzi.day2.enumerates.WorkspaceType;
import com.ragalzi.day2.models.Building;
import com.ragalzi.day2.models.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
        List<Workspace> findByName(String name);

        Workspace findByNameAndBuilding(String name, Building building);

        @Query("SELECT w FROM Workspace w JOIN w.building b WHERE w.type = :type AND b.city = :city")
        List<Workspace> findByTypeAndCity(
                        @Param("type") WorkspaceType type,
                        @Param("city") String city);

}

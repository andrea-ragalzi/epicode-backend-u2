package com.ragalzi.project.models;

import java.util.List;

import com.ragalzi.project.enumerates.WorkspaceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workspaces")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkspaceType type;

    @Column(name = "max_occupancy", nullable = false)
    private int maxOccupancy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Building building;

    @OneToMany(mappedBy = "workspace")
    private List<Reservation> reservations;

    public Workspace(
            String description, WorkspaceType type, int maxOccupancy) {
        this.description = description;
        this.type = type;
        this.maxOccupancy = maxOccupancy;
    }

}
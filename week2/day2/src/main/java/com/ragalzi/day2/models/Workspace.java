package com.ragalzi.day2.models;

import java.util.List;

import com.ragalzi.day2.enumerates.WorkspaceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkspaceType type;

    @Column(nullable = false)
    private int maxOccupancy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Building building;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public Workspace(
            String name, String description, WorkspaceType type,
            int maxOccupancy) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.maxOccupancy = maxOccupancy;
    }

}
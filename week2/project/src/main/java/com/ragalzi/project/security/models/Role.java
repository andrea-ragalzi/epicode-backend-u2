package com.ragalzi.project.security.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ragalzi.project.security.enumerates.UserRole;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    // Aggiungi questa annotazione per permettere la join
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}

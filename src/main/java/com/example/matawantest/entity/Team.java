package com.example.matawantest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Team model
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[A-Z0-9]+")
    private String acronym;

    @NotNull
    private Double budget;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Player> players;

    /**
     * Instantiates a new Team without players
     *
     * @param name    the name
     * @param acronym the acronym
     * @param budget  the budget
     */
    public Team(String name, String acronym, Double budget) {
        this.name = name;
        this.acronym = acronym;
        this.budget = budget;
        this.players = new ArrayList<>();
    }
}

package com.example.matawantest.repository;

import com.example.matawantest.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Team repository.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    /**
     * Find a team by name
     *
     * @param name The name of the team
     * @return The team or null
     */
    Team findByName(String name);

    /**
     * Find a team by id
     *
     * @param id The id of the team
     * @return The team or null
     */
    Team findById(Long id);
}

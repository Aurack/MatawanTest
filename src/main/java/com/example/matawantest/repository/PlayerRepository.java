package com.example.matawantest.repository;

import com.example.matawantest.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Player repository.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /**
     * Find a player by name
     *
     * @param name The name of the player
     * @return The player or null
     */
    Player findByName(String name);

    /**
     * Find a player by id
     *
     * @param id The id of the player
     * @return The player or null
     */
    Player findById(Long id);
}

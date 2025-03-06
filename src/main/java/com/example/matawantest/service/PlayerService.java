package com.example.matawantest.service;

import com.example.matawantest.entity.Player;
import com.example.matawantest.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Player service.
 */
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Find a player by its id
     *
     * @param id The player's id
     * @return The player if found or null
     */
    public Player findById(Long id) {
        return this.playerRepository.findById(id);
    }

    /**
     * Find all players
     *
     * @return All the players
     */
    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }

    /**
     * Create or update a player
     *
     * @param player The player data
     * @return The player created/updated or null
     */
    public Player save(Player player) {
        return this.playerRepository.save(player);
    }

    /**
     * Delete a player
     *
     * @param player The player to delete
     */
    public void delete(Player player) {
        this.playerRepository.delete(player);
    }

    /**
     * Find a player by its name
     *
     * @param name The player's name
     * @return The player if found or null
     */
    public Player findByName(String name) {
        return this.playerRepository.findByName(name);
    }
}

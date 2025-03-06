package com.example.matawantest.controller;

import com.example.matawantest.entity.Player;
import com.example.matawantest.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Player controller.
 */
@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Find all players
     *
     * @return All the players
     */
    @GetMapping()
    public ResponseEntity<List<Player>> findAll() {
        List<Player> players = this.playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    /**
     * Find a player by its id
     *
     * @param playerId The player's id
     * @return The player if found or a NOT FOUND error (404)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable("id") Long playerId) {
        Player player = this.playerService.findById(playerId);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Find a player by its name
     *
     * @param name The player's name
     * @return The player if found or a NOT FOUND error (404)
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Player> findByName(@PathVariable("name") String name) {
        Player player = this.playerService.findByName(name);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create or update a player
     *
     * @param player The player data
     * @return The player created/updated or a NOT FOUND error (404)
     */
    @PostMapping()
    public ResponseEntity<Player> save(@Valid @RequestBody Player player) {
        Player savedPlayer = this.playerService.save(player);
        if (savedPlayer != null) {
            return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a player
     *
     * @param player The player to delete
     * @return The player deleted
     */
    @DeleteMapping()
    public ResponseEntity<Player> delete(@Valid @RequestBody Player player) {
        this.playerService.delete(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}

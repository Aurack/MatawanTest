package com.example.matawantest.controller;

import com.example.matawantest.entity.Player;
import com.example.matawantest.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public ResponseEntity<List<Player>> findAll() {
        List<Player> players = this.playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable("id") Long playerId) {
        Player player = this.playerService.findById(playerId);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Player> findByName(@PathVariable("name") String name) {
        Player player = this.playerService.findByName(name);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Player> save(@RequestBody Player player) {
        Player savedPlayer = this.playerService.save(player);
        if (savedPlayer != null) {
            return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<Player> delete(@RequestBody Player player) {
        this.playerService.delete(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}

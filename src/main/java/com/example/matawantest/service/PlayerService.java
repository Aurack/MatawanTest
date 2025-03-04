package com.example.matawantest.service;

import com.example.matawantest.entity.Player;
import com.example.matawantest.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findById(Long id) {
        return this.playerRepository.findById(id);
    }

    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }

    public Player save(Player player) {
        return this.playerRepository.save(player);
    }

    public void delete(Player player) {
        this.playerRepository.delete(player);
    }

    public Player findByName(String name) {
        return this.playerRepository.findByName(name);
    }
}

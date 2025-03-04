package com.example.matawantest.service;

import com.example.matawantest.entity.Team;
import com.example.matawantest.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findById(Long id) {
        return this.teamRepository.findById(id);
    }

    public Team save(Team team) {
        return this.teamRepository.save(team);
    }

    public void delete(Team team) {
        this.teamRepository.delete(team);
    }

    public List<Team> findAll() {
        return this.teamRepository.findAll();
    }

    public Team findByName(String name) {
        return this.teamRepository.findByName(name);
    }
}

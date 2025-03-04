package com.example.matawantest.service;

import com.example.matawantest.entity.Team;
import com.example.matawantest.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findById(int id) {
        return this.teamRepository.findById(id).get();
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

    public List<Team> findByTeamName(String teamName) {
        return this.teamRepository.findByName(teamName);
    }

    public Optional<Team> findByTeamId(int teamId) {
        return this.teamRepository.findById(teamId);
    }
}

package com.example.matawantest.controller;

import com.example.matawantest.entity.Team;
import com.example.matawantest.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = this.teamService.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable("id") Long teamId) {
        Team team = this.teamService.findById(teamId);
        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Team> findByName(@PathVariable("name") String name) {
        Team team = this.teamService.findByName(name);
        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Team> save(@RequestBody Team team) {
        Team savedTeam = this.teamService.save(team);
        if (savedTeam != null) {
            return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<Team> delete(@RequestBody Team team) {
        this.teamService.delete(team);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}

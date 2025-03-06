package com.example.matawantest.controller;

import com.example.matawantest.entity.Team;
import com.example.matawantest.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Team controller.
 */
@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Find all teams
     *
     * @return The list of all the teams
     */
    @GetMapping()
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = this.teamService.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    /**
     * Find a team by its id
     *
     * @param teamId The team's id
     * @return The team found or an error 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable("id") Long teamId) {
        Team team = this.teamService.findById(teamId);
        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Find a team by its name
     *
     * @param name The team's name
     * @return The team found or an error 404
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Team> findByName(@PathVariable("name") String name) {
        Team team = this.teamService.findByName(name);
        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Find paged and sorted teams by page.
     *
     * @param page      the page
     * @param size      the size
     * @param sortBy    the sort by
     * @param direction the direction
     * @return the page
     */
    @GetMapping("/paged")
    public Page<Team> findPagedAndSortedTeamsBy(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        return teamService.findPagedAndSortedTeamsBy(page, size, sortBy, direction);
    }

    /**
     * Create a new team
     *
     * @param team The team data
     * @return The team created or a BAD REQUEST error (400)
     */
    @PostMapping()
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team team) {
        Team savedTeam = this.teamService.createTeam(team);
        if (savedTeam != null) {
            return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update an existing team
     *
     * @param updatedTeam The updated team data
     * @return The team updated or a NOT FOUND error (404)
     */
    @PutMapping()
    public ResponseEntity<Team> updateTeam(@Valid @RequestBody Team updatedTeam) {
        Team team = this.teamService.findById(updatedTeam.getId());
        if (team != null) {
            Team savedTeam = this.teamService.save(team);
            if (savedTeam != null) {
                return new ResponseEntity<>(savedTeam, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a team
     *
     * @param team The team to delete
     * @return The deleted team
     */
    @DeleteMapping()
    public ResponseEntity<Team> delete(@Valid @RequestBody Team team) {
        this.teamService.delete(team);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}

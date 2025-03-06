package com.example.matawantest.service;

import com.example.matawantest.entity.Player;
import com.example.matawantest.entity.Team;
import com.example.matawantest.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Team service
 */
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Find team by Id
     *
     * @param id The id of the team
     * @return The team found or null
     */
    public Team findById(Long id) {
        return this.teamRepository.findById(id);
    }

    /**
     * Update a team
     *
     * @param team The team to update
     * @return The team updated
     */
    public Team save(Team team) {
        return this.teamRepository.save(team);
    }

    /**
     * Delete a team object from the database
     *
     * @param team The team to delete
     */
    public void delete(Team team) {
        this.teamRepository.delete(team);
    }

    /**
     * Find all teams
     *
     * @return The list of all the teams
     */
    public List<Team> findAll() {
        return this.teamRepository.findAll();
    }

    /**
     * Find by team by its name
     *
     * @param name The name of the team
     * @return team The team found or null
     */
    public Team findByName(String name) {
        return this.teamRepository.findByName(name);
    }

    /**
     * Find paged and sorted teams
     *
     * @param page      the page
     * @param size      the size
     * @param sortBy    the sort by
     * @param direction the direction
     * @return the page
     */
    public Page<Team> findPagedAndSortedTeamsBy(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return teamRepository.findAll(pageable);
    }

    /**
     * Create a team
     *
     * @param teamData The team as JSON
     * @return The team created
     */
    public Team createTeam(Team teamData) {
        if (teamData.getName() == null || teamData.getAcronym() == null || teamData.getBudget() == null) {
            throw new IllegalArgumentException("Fields name, acronym and budget are required");
        }

        Team team = new Team(teamData.getName(), teamData.getAcronym(), teamData.getBudget());

        if (teamData.getPlayers() != null && !teamData.getPlayers().isEmpty()) {
            List<Player> players = teamData.getPlayers().stream()
                    .peek(p -> p.setTeam(team))
                    .collect(Collectors.toList());
            team.setPlayers(players);
        }

        return this.teamRepository.save(team);
    }
}

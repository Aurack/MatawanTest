package com.example.matawantest;

import com.example.matawantest.entity.Team;
import com.example.matawantest.repository.PlayerRepository;
import com.example.matawantest.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @BeforeAll
    public void setup() {
        teamRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @Test
    @Order(1)
    void createTeam() throws Exception {
        log.info("\n------------------- Creating team TEST 1 -------------------\n");
        String teamData = """
                {
                    "name": "team1",
                    "acronym": "T1",
                    "budget": 10000,
                    "players": [
                        {
                            "name": "player1",
                            "position": "position1"
                        },
                        {
                            "name": "player2",
                            "position": "position2"
                        }
                    ]
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/team")
                        .contentType("application/json")
                        .content(teamData))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void createTeamWithSameName() throws Exception {
        log.info("\n------------------- Creating team TEST 2 -------------------\n");
        String teamData2 = """
                    {
                        "name": "team1",
                        "acronym": "T2",
                        "budget": 10000
                    }
                    """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/team")
                        .contentType("application/json")
                        .content(teamData2))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void createTeamWithSameAcronym() throws Exception {
        log.info("\n------------------- Creating team TEST 3 -------------------\n");
        String teamData3 = """
                {
                    "name": "team2",
                    "acronym": "T1",
                    "budget": 10000
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/team")
                        .contentType("application/json")
                        .content(teamData3))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    void createTeamWithSamePlayer() throws Exception {
        log.info("\n------------------- Creating team TEST 4 -------------------\n");
        String teamData = """
                {
                    "name": "team2",
                    "acronym": "T2",
                    "budget": 10000,
                    "players": [
                        {
                            "name": "player1",
                            "position": "position1"
                        }
                    ]
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/team")
                        .contentType("application/json")
                        .content(teamData))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    void getTeams() throws Exception {
        log.info("\n------------------- Creating team TEST 5 -------------------\n");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/team?page=0&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void getPaginatedTeams() throws Exception {
        log.info("\n------------------- Creating team TEST 6 -------------------\n");
        for (int i = 2; i <= 20; i++) {
            Team team = new Team("Team " + i, "T" + i, 1000.0 * i);
            teamRepository.save(team);
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/api/team/paged?page=0&size=5&sortBy=name&direction=asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.totalElements").value(20));
    }

    @Test
    @Order(7)
    void getPaginatedTeamsByAcronym() throws Exception {
        log.info("\n------------------- Creating team TEST 7 -------------------\n");
        for (int i = 100; i >80; i--) {
            Team team = new Team("Team " + i, "T" + i, 1000.0 * i);
            teamRepository.save(team);
        }
        mockMvc.perform(MockMvcRequestBuilders.get("/api/team/paged?page=0&size=10&sortBy=acronym&direction=desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].acronym").value("T99"))
                .andExpect(jsonPath("$.content[1].acronym").value("T98"));
    }
}

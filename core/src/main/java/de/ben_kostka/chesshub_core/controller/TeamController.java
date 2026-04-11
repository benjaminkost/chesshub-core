package de.ben_kostka.chesshub_core.controller;

import de.ben_kostka.chesshub_core.api.TeamsApi;
import de.ben_kostka.chesshub_core.api.dto.Team;
import de.ben_kostka.chesshub_core.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController implements TeamsApi {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<Team> getTeamById(Long teamId) {
        return ResponseEntity.ok(teamService.getTeamById(teamId));
    }
}

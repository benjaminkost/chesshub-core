package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.TeamDto;

import java.util.List;

public interface TeamService {
    TeamDto getTeamById(Long teamId);

    List<TeamDto> getAllTeams();
}

package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.Team;

public interface TeamService {
    Team getTeamById(Long teamId);
}

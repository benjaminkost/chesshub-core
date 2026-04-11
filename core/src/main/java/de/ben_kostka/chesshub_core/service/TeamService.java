package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.Team;
import de.ben_kostka.chesshub_core.api.dto.TeamMember;

import java.util.List;

public interface TeamService {
    Team getTeamById(Long teamId);
    List<TeamMember> getTeamMembers(Long teamId);
}

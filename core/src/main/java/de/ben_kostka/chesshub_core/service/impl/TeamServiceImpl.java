package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.TeamMember;
import de.ben_kostka.chesshub_core.api.dto.Team;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import de.ben_kostka.chesshub_core.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getTeamById(Long teamId) {
        de.ben_kostka.chesshub_core.model.Team teamEntity = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId.toString()));
                
        Team dto = new Team();
        dto.setId(teamEntity.getId());
        dto.setName(teamEntity.getName());
        if (teamEntity.getClub() != null) {
            dto.setClubId(teamEntity.getClub().getId());
        }
        return dto;
    }

    @Override
    public List<TeamMember> getTeamMembers(Long teamId) {
        // Many-to-many relationship tracking team role isn't fully defined in JPA yet
        return Collections.emptyList();
    }
}

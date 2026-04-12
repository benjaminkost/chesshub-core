package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.TeamMember;
import de.ben_kostka.chesshub_core.api.dto.Team;
import de.ben_kostka.chesshub_core.api.dto.TeamRole;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.repository.TeamMembershipRepository;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import de.ben_kostka.chesshub_core.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    
    private final TeamRepository teamRepository;
    private final TeamMembershipRepository teamMembershipRepository;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMembershipRepository teamMembershipRepository){
        this.teamRepository = teamRepository;
        this.teamMembershipRepository = teamMembershipRepository;
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
            dto.setClubName(teamEntity.getClub().getName());
        }
        
        if (teamEntity.getLeader() != null) {
            dto.setAdminId(teamEntity.getLeader().getId());
            dto.setAdminName(teamEntity.getLeader().getFirstName() + " " + teamEntity.getLeader().getLastName());
        }
        
        List<TeamMember> members = teamMembershipRepository.findByTeamId(teamId).stream().map(tm -> {
            TeamMember member = new TeamMember();
            member.setId(tm.getUser().getId());
            member.setName(tm.getUser().getFirstName() + " " + tm.getUser().getLastName());
            member.setUserName(tm.getUser().getUsername());
            if (tm.getRoles() != null && !tm.getRoles().isEmpty()) {
                member.setRoles(Arrays.stream(tm.getRoles().split(","))
                        .map(r -> TeamRole.valueOf(r.trim()))
                        .collect(Collectors.toList()));
            } else {
                member.setRoles(Collections.emptyList());
            }
            return member;
        }).collect(Collectors.toList());
        
        dto.setMembers(members);
        
        return dto;
    }
}

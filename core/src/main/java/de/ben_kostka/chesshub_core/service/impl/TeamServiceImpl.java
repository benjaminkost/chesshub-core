package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.TeamMember;
import de.ben_kostka.chesshub_core.api.dto.TeamDto;
import de.ben_kostka.chesshub_core.api.dto.TeamRole;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.model.Team;
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
    public TeamDto getTeamById(Long teamId) {
        Team teamEntity = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId.toString()));

        TeamDto dto = new TeamDto();
        dto.setId(teamEntity.getId());
        dto.setName(teamEntity.getName());
        
        if (teamEntity.getClub() != null) {
            dto.setClubId(teamEntity.getClub().getId());
            dto.setClubName(teamEntity.getClub().getName());
        }
        
        if (teamEntity.getLeader() != null) {
            dto.setAdminId(teamEntity.getLeader().getId());
            dto.setAdminFirstName(teamEntity.getLeader().getFirstName());
            dto.setAdminLastName(teamEntity.getLeader().getLastName());
        }
        
        List<TeamMember> members = teamMembershipRepository.findByTeamId(teamId).stream().map(tm -> {
            TeamMember member = new TeamMember();
            member.setId(tm.getUser().getId());
            member.setFirstName(tm.getUser().getFirstName());
            member.setLastName(tm.getUser().getLastName());
            member.setUserName(tm.getUser().getUsername());
            if (tm.getRoles() != null && !tm.getRoles().isEmpty()) {
                member.setRoles(Arrays.stream(tm.getRoles().split(","))
                        .map(r -> TeamRole.valueOf(r.trim()))
                        .collect(Collectors.toList()));
            } else {
                member.setRoles(Collections.emptyList());
            }
            return member;
        }).toList();
        
        dto.setMembers(members);
        
        return dto;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> allTeamEntities = teamRepository.findAll();

        List<TeamDto> allTeamsDto = allTeamEntities.stream().map(t -> {
            TeamDto dto = new TeamDto();

            dto.setId(t.getId());
            dto.setAdminId(t.getLeader().getId());
            dto.setAdminFirstName(t.getLeader().getFirstName());
            dto.setAdminLastName(t.getLeader().getLastName());
            dto.setClubId(t.getClub().getId());
            dto.setClubName(t.getClub().getName());
            dto.setMembers(t.getTeamMemberships().stream().map(m -> {
                TeamMember teamMember = new TeamMember();
                teamMember.setId(m.getUser().getId());
                teamMember.setFirstName(m.getUser().getFirstName());
                teamMember.setLastName(m.getUser().getLastName());
                return teamMember;
            }).toList());
            dto.setName(t.getName());

            return dto;
        }).toList();

        return allTeamsDto;
    }
}

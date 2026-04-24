package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.model.Club;
import de.ben_kostka.chesshub_core.api.dto.ClubMember;
import de.ben_kostka.chesshub_core.api.dto.ClubRole;
import de.ben_kostka.chesshub_core.api.dto.ClubMemberStatus;
import de.ben_kostka.chesshub_core.api.dto.ClubSimple;
import de.ben_kostka.chesshub_core.api.dto.TeamSimple;
import de.ben_kostka.chesshub_core.repository.ClubMembershipRepository;
import de.ben_kostka.chesshub_core.repository.ClubRepository;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import de.ben_kostka.chesshub_core.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final TeamRepository teamRepository;
    private final ClubMembershipRepository clubMembershipRepository;
    
    public ClubServiceImpl(ClubRepository clubRepository, TeamRepository teamRepository, ClubMembershipRepository clubMembershipRepository){
        this.clubRepository = clubRepository;
        this.teamRepository = teamRepository;
        this.clubMembershipRepository = clubMembershipRepository;
    }

    @Override
    public List<ClubSimple> getAllClubs() {
        return clubRepository.findAll().stream().map(club -> {
            ClubSimple dto = new ClubSimple();
            dto.setId(club.getId());
            dto.setName(club.getName());
            if (club.getPresident() != null) {
                dto.setAdminId(club.getPresident().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public de.ben_kostka.chesshub_core.api.dto.Club getClubById(Long clubId) {
        Club clubEntity = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("Club", "id", clubId.toString()));
                
        de.ben_kostka.chesshub_core.api.dto.Club dto = new de.ben_kostka.chesshub_core.api.dto.Club();
        dto.setId(clubEntity.getId());
        dto.setName(clubEntity.getName());
        dto.setAddress(clubEntity.getAddress());
        return dto;
    }

    @Override
    public List<ClubMember> getClubMembers(Long clubId) {
        clubRepository.findById(clubId).orElseThrow(() -> new ResourceNotFoundException("Club", "id", clubId.toString()));
        return clubMembershipRepository.findByClubId(clubId).stream().map(cm -> {
            ClubMember member = new ClubMember();
            member.setUserId(cm.getUser().getId());
            member.setFirstName(cm.getUser().getFirstName());
            member.setLastName(cm.getUser().getLastName());
            member.setStatus(ClubMemberStatus.valueOf(cm.getStatus()));
            if (cm.getRoles() != null && !cm.getRoles().isEmpty()) {
                member.setRole(Arrays.stream(cm.getRoles().split(","))
                        .map(r -> ClubRole.valueOf(r.trim()))
                        .collect(Collectors.toList()));
            } else {
                member.setRole(Collections.emptyList());
            }
            return member;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TeamSimple> getTeamsByClub(Long clubId) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getClub() != null && team.getClub().getId().equals(clubId))
                .map(team -> {
                    TeamSimple simple = new TeamSimple();
                    simple.setId(team.getId());
                    simple.setName(team.getName());
                    simple.setClubName(team.getClub().getName());
                    if (team.getLeader() != null) {
                        simple.setAdminId(team.getLeader().getId());
                    }
                    return simple;
                }).collect(Collectors.toList());
    }
}


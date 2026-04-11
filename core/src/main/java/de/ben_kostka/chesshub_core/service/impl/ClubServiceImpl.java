package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.model.Club;
import de.ben_kostka.chesshub_core.api.dto.ClubMember;
import de.ben_kostka.chesshub_core.api.dto.ClubSimple;
import de.ben_kostka.chesshub_core.api.dto.TeamSimple;
import de.ben_kostka.chesshub_core.repository.ClubRepository;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import de.ben_kostka.chesshub_core.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final TeamRepository teamRepository;
    
    public ClubServiceImpl(ClubRepository clubRepository, TeamRepository teamRepository){
        this.clubRepository = clubRepository;
        this.teamRepository = teamRepository;
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
        dto.setAddress("Unknown"); // address missing in basic JPA Club model currently
        return dto;
    }

    @Override
    public List<ClubMember> getClubMembers(Long clubId) {
        Club clubEntity = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("Club", "id", clubId.toString()));
        // Advanced membership missing in current JPA model, dummy logic:
        return Collections.emptyList();
    }

    @Override
    public List<TeamSimple> getTeamsByClub(Long clubId) {
        // Assume team has club_id relation (to be verified / implemented)
        return Collections.emptyList();
    }
}


package de.ben_kostka.chesshub_core.controller;

import de.ben_kostka.chesshub_core.api.ClubsApi;
import de.ben_kostka.chesshub_core.api.dto.Club;
import de.ben_kostka.chesshub_core.api.dto.ClubMember;
import de.ben_kostka.chesshub_core.api.dto.ClubSimple;
import de.ben_kostka.chesshub_core.api.dto.TeamSimple;
import de.ben_kostka.chesshub_core.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClubController implements ClubsApi {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @Override
    public ResponseEntity<List<ClubSimple>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @Override
    public ResponseEntity<Club> getClubById(Long clubId) {
        return ResponseEntity.ok(clubService.getClubById(clubId));
    }

    @Override
    public ResponseEntity<List<ClubMember>> getClubMembers(Long clubId) {
        return ResponseEntity.ok(clubService.getClubMembers(clubId));
    }

    @Override
    public ResponseEntity<List<TeamSimple>> getTeamsByClub(Long clubId) {
        return ResponseEntity.ok(clubService.getTeamsByClub(clubId));
    }
}

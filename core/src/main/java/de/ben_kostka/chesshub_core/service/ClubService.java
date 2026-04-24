package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.Club;
import de.ben_kostka.chesshub_core.api.dto.ClubMember;
import de.ben_kostka.chesshub_core.api.dto.ClubSimple;
import de.ben_kostka.chesshub_core.api.dto.TeamSimple;

import java.util.List;

public interface ClubService {
    List<ClubSimple> getAllClubs();
    Club getClubById(Long clubId);
    List<ClubMember> getClubMembers(Long clubId);
    List<TeamSimple> getTeamsByClub(Long clubId);
}

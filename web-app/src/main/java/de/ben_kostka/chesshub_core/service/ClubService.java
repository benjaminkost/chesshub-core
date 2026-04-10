package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.payload.ClubDto;

import java.util.List;

public interface ClubService {
    ClubDto createClub(ClubDto clubDto);

    List<ClubDto> getAllClubDto();
}

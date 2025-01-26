package de.ben_kostka.benchesster.service;

import de.ben_kostka.benchesster.payload.ClubDto;

import java.util.List;

public interface ClubService {
    ClubDto createClub(ClubDto clubDto);

    List<ClubDto> getAllClubDto();
}

package de.ben_kostka.benchesster.service.impl;

import de.ben_kostka.benchesster.model.Club;
import de.ben_kostka.benchesster.payload.ClubDto;
import de.ben_kostka.benchesster.repository.ClubRepository;
import de.ben_kostka.benchesster.service.ClubService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    public ClubServiceImpl(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    @Override
    public ClubDto createClub(ClubDto clubDto) {

        Club club = mapToEntity(clubDto);

        Club newClub = clubRepository.save(club);

        ClubDto postResponse = mapToDto(newClub);

        return postResponse;
    }

    @Override
    public List<ClubDto> getAllClubDto() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> mapToDto(club)).collect(Collectors.toList());
    }

    private ClubDto mapToDto(Club club){
        ClubDto clubDto = new ClubDto();
        clubDto.setClub_ID(club.getClub_ID());
        clubDto.setPresident(club.getPresident());
        clubDto.setName(club.getName());

        return clubDto;
    }

    private Club mapToEntity(ClubDto clubDto){
        Club club = new Club();
        club.setClub_ID(clubDto.getClub_ID());
        club.setPresident(clubDto.getPresident());
        club.setName(clubDto.getName());

        return club;
    }
}

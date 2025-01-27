package de.ben_kostka.benchesster.controller;

import de.ben_kostka.benchesster.payload.ClubDto;
import de.ben_kostka.benchesster.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }
    @PostMapping
    public ResponseEntity<ClubDto> create(@RequestBody ClubDto clubDto){
        return new ResponseEntity<>(clubService.createClub(clubDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClubDto> getAllClubs(){
        return clubService.getAllClubDto();
    }


}

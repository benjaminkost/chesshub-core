package de.ben_kostka.benchesster.controller;

import de.ben_kostka.benchesster.payload.GameDto;
import de.ben_kostka.benchesster.service.UploadGameService;
import de.ben_kostka.benchesster.service.impl.UploadGameServiceImpl;
import jakarta.persistence.PostRemove;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class UploadGameController {
    private UploadGameService uploadGameService;
    public UploadGameController(UploadGameService uploadGameService){
        this.uploadGameService = uploadGameService;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getAvailableUploadMethods(){
        List<String> methods = uploadGameService.getAvailableUploadMethods();
        return new ResponseEntity<>(methods, HttpStatus.OK);
    }

    @PostMapping(value = "/fromFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> uploadGameFromFile(@RequestParam("gameFile") MultipartFile gameFile){
        GameDto gameDto = uploadGameService.uploadFromFile(gameFile);
        return new ResponseEntity<>(gameDto, HttpStatus.CREATED);
    }

}

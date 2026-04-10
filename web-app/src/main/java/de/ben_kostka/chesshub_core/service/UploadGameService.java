package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.payload.GameDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadGameService {
    List<String> getAvailableUploadMethods();

    GameDto uploadOnBoard(GameDto gameDto);
    GameDto uploadFromFile(MultipartFile multipartFile) throws Exception;
    GameDto uploadFromImage(MultipartFile multipartFile);
}

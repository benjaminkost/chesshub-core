package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.GameDto;
import de.ben_kostka.chesshub_core.api.dto.GameRequest;
import java.util.List;

public interface GameService {
    GameDto createGame(GameRequest gameRequest);
    GameDto getGameById(Long gameId);
    List<GameDto> getGamesByClub(Long clubId);
    List<GameDto> getGamesByUser(Long userId);
}

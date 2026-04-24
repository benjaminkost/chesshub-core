package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.Game;
import de.ben_kostka.chesshub_core.api.dto.GameRequest;
import java.util.List;

public interface GameService {
    Game createGame(GameRequest gameRequest);
    Game getGameById(Long gameId);
    List<Game> getGamesByClub(Long clubId);
    List<Game> getGamesByUser(Long userId);
}

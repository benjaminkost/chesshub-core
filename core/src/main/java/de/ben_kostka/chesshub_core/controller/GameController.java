package de.ben_kostka.chesshub_core.controller;

import de.ben_kostka.chesshub_core.api.GamesApi;
import de.ben_kostka.chesshub_core.api.dto.Game;
import de.ben_kostka.chesshub_core.api.dto.GameRequest;
import de.ben_kostka.chesshub_core.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GamesApi {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<Game> createGame(GameRequest gameRequest) {
        Game gameResponse = gameService.createGame(gameRequest);
        return new ResponseEntity<>(gameResponse, HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<Game> getGameById(Long gameId) {
        return ResponseEntity.ok(gameService.getGameById(gameId));
    }

    @Override
    public ResponseEntity<java.util.List<Game>> getGamesByClub(Long clubId) {
        return ResponseEntity.ok(gameService.getGamesByClub(clubId));
    }

    @Override
    public ResponseEntity<java.util.List<Game>> getGamesByUser(Long userId) {
        return ResponseEntity.ok(gameService.getGamesByUser(userId));
    }
}

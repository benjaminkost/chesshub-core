package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.Game;
import de.ben_kostka.chesshub_core.api.dto.GameRequest;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.repository.GameRepository;
import de.ben_kostka.chesshub_core.service.GameService;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(GameRequest gameRequest) {
        if (gameRequest.getMoves() == null || gameRequest.getMoves().isEmpty()) {
            throw new IllegalArgumentException("Moves Data cannot be empty");
        }

        // Map directly from DTO to Entity
        de.ben_kostka.chesshub_core.model.Game gameEntity = new de.ben_kostka.chesshub_core.model.Game();
        gameEntity.setWhite_player_name(gameRequest.getWhitePlayerName());
        gameEntity.setBlack_player_name(gameRequest.getBlackPlayerName());
        gameEntity.setEvent(gameRequest.getEvent());
        gameEntity.setMoves(gameRequest.getMoves());
        
        if (gameRequest.getDate() != null) {
            gameEntity.setDate(Date.from(gameRequest.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        
        de.ben_kostka.chesshub_core.model.Game savedEntity = gameRepository.save(gameEntity);
        return mapToDto(savedEntity);
    }

    @Override
    public Game getGameById(Long gameId) {
        de.ben_kostka.chesshub_core.model.Game entity = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId.toString()));
        return mapToDto(entity);
    }

    private Game mapToDto(de.ben_kostka.chesshub_core.model.Game entity) {
        Game dto = new Game();
        dto.setId(entity.getId());
        dto.setEvent(entity.getEvent());
        dto.setSite(entity.getSite());
        if (entity.getDate() != null) {
            dto.setDate(LocalDate.ofInstant(entity.getDate().toInstant(), ZoneId.systemDefault()));
        }
        dto.setRound(entity.getRound());
        dto.setWhitePlayerName(entity.getWhite_player_name());
        dto.setBlackPlayerName(entity.getBlack_player_name());
        dto.setResult(entity.getResult());
        dto.setMoves(entity.getMoves());
        return dto;
    }
}

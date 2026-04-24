package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.GameDto;
import de.ben_kostka.chesshub_core.api.dto.GameRequest;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.model.Game;
import de.ben_kostka.chesshub_core.model.User;
import de.ben_kostka.chesshub_core.repository.GameRepository;
import de.ben_kostka.chesshub_core.service.GameService;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameServiceImpl(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public GameDto createGame(GameRequest gameRequest) {
        if (gameRequest.getMoves() == null || gameRequest.getMoves().isEmpty()) {
            throw new IllegalArgumentException("Moves Data cannot be empty");
        }

        // Map directly from DTO to Entity
        Game gameEntity = new Game();
        if (gameRequest.getWhitePlayer().getId() != null) {
            User existingUserWhite = new User();
            existingUserWhite.setId(gameRequest.getWhitePlayer().getId());
            gameEntity.setWhite_user(existingUserWhite);
        } else {
            gameEntity.setWhite_user(null);
            gameEntity.setBlack_player_name(gameRequest.getWhitePlayer().getLastName()+" "+gameRequest.getBlackPlayer().getLastName());
        }

        if (gameRequest.getBlackPlayer().getId() != null) {
            User existingUserBlack = new User();
            existingUserBlack.setId(gameRequest.getBlackPlayer().getId());
            gameEntity.setBlack_user(existingUserBlack);
        } else {
            gameEntity.setBlack_user(null);
            gameEntity.setBlack_player_name(gameRequest.getBlackPlayer().getFirstName()+" "+gameRequest.getBlackPlayer().getLastName());
        }

        gameEntity.setEvent(gameRequest.getEvent());
        gameEntity.setMoves(gameRequest.getMoves());
        
        if (gameRequest.getTeamId() != null) {
            de.ben_kostka.chesshub_core.model.Team team = teamRepository.findById(gameRequest.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team", "id", gameRequest.getTeamId().toString()));
            gameEntity.setTeam(team);
        }
        
        if (gameRequest.getDate() != null) {
            gameEntity.setDate(Date.from(gameRequest.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        
        Game savedEntity = gameRepository.save(gameEntity);
        return mapToDto(savedEntity);
    }

    @Override
    public GameDto getGameById(Long gameId) {
        Game entity = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId.toString()));
        return mapToDto(entity);
    }

    @Override
    public List<GameDto> getGamesByClub(Long clubId) {
        return gameRepository.findByTeam_Club_Id(clubId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDto> getGamesByUser(Long userId) {
        return gameRepository.findByWhiteUserOrBlackUser(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private GameDto mapToDto(Game entity) {
        GameDto dto = new GameDto();
        dto.setId(entity.getId());
        dto.setEvent(entity.getEvent());
        dto.setSite(entity.getSite());
        if (entity.getDate() != null) {
            dto.setDate(LocalDate.ofInstant(entity.getDate().toInstant(), ZoneId.systemDefault()));
        }
        dto.setRound(entity.getRound() != 0 ? entity.getRound() : null);
        dto.setWhitePlayerName(entity.getWhite_player_name());
        dto.setBlackPlayerName(entity.getBlack_player_name());
        dto.setResult(entity.getResult());
        dto.setMoves(entity.getMoves());
        dto.setOpening(entity.getOpening());
        dto.setBoard(entity.getBoard());
        
        if (entity.getTeam() != null) {
            dto.setTeamId(entity.getTeam().getId());
            dto.setTeamName(entity.getTeam().getName());
        }
        
        if (entity.getWhite_user() != null) {
            dto.setWhitePlayerId(entity.getWhite_user().getId());
        }
        if (entity.getBlack_user() != null) {
            dto.setBlackPlayerId(entity.getBlack_user().getId());
        }
        return dto;
    }
}

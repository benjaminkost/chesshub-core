package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.GameDto;
import de.ben_kostka.chesshub_core.api.dto.GamePlayer;
import de.ben_kostka.chesshub_core.api.dto.GameRequest;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.model.Game;
import de.ben_kostka.chesshub_core.model.Team;
import de.ben_kostka.chesshub_core.model.User;
import de.ben_kostka.chesshub_core.repository.GameRepository;
import de.ben_kostka.chesshub_core.repository.UserRepository;
import de.ben_kostka.chesshub_core.service.GameService;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    private GameDto mapAndSave(GameDto gameDto) {
        Game gameEntity = new Game();
        gameEntity.setId(gameDto.getId());
        if (gameDto.getWhitePlayer() != null && gameDto.getWhitePlayer().getId() != null) {
            gameEntity.setWhite_user(userRepository.getReferenceById(gameDto.getWhitePlayer().getId()));
            gameEntity.setWhite_player_name(gameDto.getWhitePlayer().getFirstName()+" "+gameDto.getWhitePlayer().getLastName());
        } else {
            gameEntity.setWhite_user(null);
            gameEntity.setWhite_player_name(null);
        }

        if (gameDto.getBlackPlayer() != null) {
            if (gameDto.getBlackPlayer().getId() != null) {
                gameEntity.setBlack_user(userRepository.getReferenceById(gameDto.getBlackPlayer().getId()));
            }
            gameEntity.setBlack_player_name(gameDto.getBlackPlayer().getFirstName()+" "+gameDto.getBlackPlayer().getLastName());
        } else {
            gameEntity.setBlack_user(null);
            gameEntity.setBlack_player_name(null);
        }

        gameEntity.setResult(gameDto.getResult());
        gameEntity.setRound(gameDto.getRound() != null ? gameDto.getRound() : 0);
        gameEntity.setSite(gameDto.getSite());
        gameEntity.setBoard(gameDto.getBoard());
        gameEntity.setOpening(gameDto.getOpening());
        gameEntity.setEvent(gameDto.getEvent());
        gameEntity.setMoves(gameDto.getMoves());

        if (gameDto.getTeamId() != null) {
            Team team = teamRepository.findById(gameDto.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team", "id", gameDto.getTeamId().toString()));
            gameEntity.setTeam(team);
        }

        if (gameDto.getDate() != null) {
            gameEntity.setDate(Date.from(gameDto.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

       Game entity = gameRepository.save(gameEntity);
        return mapToDto(entity);
    }

    @Override
    public GameDto updateDate(GameDto gameDto) {
        gameRepository.findById(gameDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameDto.getId().toString()));

        return mapAndSave(gameDto);
    }

    @Override
    public GameDto createGame(GameRequest gameRequest) {
        if (gameRequest.getMoves() == null || gameRequest.getMoves().isEmpty()) {
            throw new IllegalArgumentException("Moves Data cannot be empty");
        }

        // Map directly from DTO to Entity
        Game gameEntity = new Game();
        if (gameRequest.getWhitePlayer() != null) {
            if (gameRequest.getWhitePlayer().getId() != null) {
                gameEntity.setWhite_user(userRepository.getReferenceById(gameRequest.getWhitePlayer().getId()));
            }
            gameEntity.setWhite_player_name(gameRequest.getWhitePlayer().getFirstName()+" "+gameRequest.getWhitePlayer().getLastName());
        } else {
            gameEntity.setWhite_user(null);
            gameEntity.setWhite_player_name(null);
        }

        if (gameRequest.getBlackPlayer() != null && gameRequest.getBlackPlayer().getId() != null) {
            gameEntity.setBlack_user(userRepository.getReferenceById(gameRequest.getBlackPlayer().getId()));
            gameEntity.setBlack_player_name(gameRequest.getBlackPlayer().getFirstName()+" "+gameRequest.getBlackPlayer().getLastName());
        } else {
            gameEntity.setBlack_user(null);
            gameEntity.setBlack_player_name(null);
        }

        gameEntity.setEvent(gameRequest.getEvent());
        gameEntity.setMoves(gameRequest.getMoves());
        
        if (gameRequest.getTeamId() != null) {
            Team team = teamRepository.findById(gameRequest.getTeamId())
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

    private GamePlayer mapUserToGamePlayer(User user) {
        GamePlayer gamePlayer = new GamePlayer();

        gamePlayer.setId(user.getId());
        gamePlayer.setFirstName(user.getFirstName());
        gamePlayer.setLastName(user.getLastName());

        return gamePlayer;
    }

    private GamePlayer mapUnregisteredNameToGamePlayer(String fullPlayerName) {
        if (fullPlayerName == null || fullPlayerName.trim().isEmpty()) return null;
        GamePlayer gamePlayer = new GamePlayer();

        String[] names = fullPlayerName.split("\\s+");
        String lastName = names[names.length-1];
        String firstNames = Arrays.stream(names)
                .limit(names.length-1)
                .collect(Collectors.joining(" "));

        gamePlayer.setFirstName(firstNames);
        gamePlayer.setLastName(lastName);

        return gamePlayer;
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
        if (entity.getWhite_user() != null) {
            dto.setWhitePlayer(mapUserToGamePlayer(entity.getWhite_user()));
        } else {
            dto.setWhitePlayer(mapUnregisteredNameToGamePlayer(entity.getWhite_player_name()));
        }
        if (entity.getBlack_user() != null) {
            dto.setBlackPlayer(mapUserToGamePlayer(entity.getBlack_user()));
        } else {
            dto.setBlackPlayer(mapUnregisteredNameToGamePlayer(entity.getBlack_player_name()));
        }

        dto.setResult(entity.getResult());
        dto.setMoves(entity.getMoves());
        dto.setOpening(entity.getOpening());
        dto.setBoard(entity.getBoard());
        
        if (entity.getTeam() != null) {
            dto.setTeamId(entity.getTeam().getId());
            dto.setTeamName(entity.getTeam().getName());
        }

        return dto;
    }
}

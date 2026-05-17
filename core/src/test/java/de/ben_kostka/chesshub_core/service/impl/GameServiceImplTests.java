package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.GameDto;
import de.ben_kostka.chesshub_core.api.dto.GamePlayer;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.model.Game;
import de.ben_kostka.chesshub_core.model.Team;
import de.ben_kostka.chesshub_core.model.User;
import de.ben_kostka.chesshub_core.repository.GameRepository;
import de.ben_kostka.chesshub_core.repository.TeamRepository;
import de.ben_kostka.chesshub_core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTests {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    private User whiteUser;
    private User blackUser;
    private Team team;
    private Game existingGame;

    @BeforeEach
    void setUp() {
        whiteUser = User.builder()
                .id(1L)
                .firstName("Magnus")
                .lastName("Carlsen")
                .username("magnuscarlsen")
                .build();

        blackUser = User.builder()
                .id(2L)
                .firstName("Ian")
                .lastName("Nepomniachtchi")
                .username("iannepomniachtchi")
                .build();

        team = new Team();
        team.setId(5L);
        team.setName("Team Alpha");

        existingGame = Game.builder()
                .id(10L)
                .event("World Championship")
                .site("Singapore")
                .result("1-0")
                .round(3)
                .moves("1. e4 e5 2. Nf3 Nc6")
                .opening("Italian Game")
                .board("Board 1")
                .white_user(whiteUser)
                .white_player_name("Magnus Carlsen")
                .black_user(blackUser)
                .black_player_name("Ian Nepomniachtchi")
                .date(Date.from(LocalDate.of(2024, 6, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .team(team)
                .build();
    }

    // =====================================================================
    // updateDate() – Happy Path Tests
    // =====================================================================

    @Test
    public void updateDate_WithRegisteredPlayers_ReturnsUpdatedGame() {
        // Given
        GamePlayer whitePlayerDto = new GamePlayer();
        whitePlayerDto.setId(1L);
        whitePlayerDto.setFirstName("Magnus");
        whitePlayerDto.setLastName("Carlsen");

        GamePlayer blackPlayerDto = new GamePlayer();
        blackPlayerDto.setId(2L);
        blackPlayerDto.setFirstName("Ian");
        blackPlayerDto.setLastName("Nepomniachtchi");

        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setWhitePlayer(whitePlayerDto);
        inputDto.setBlackPlayer(blackPlayerDto);
        inputDto.setEvent("Updated Championship");
        inputDto.setSite("New York");
        inputDto.setResult("0-1");
        inputDto.setRound(7);
        inputDto.setMoves("1. d4 d5 2. c4 e6");
        inputDto.setOpening("Queen's Gambit");
        inputDto.setBoard("Board 2");
        inputDto.setDate(LocalDate.of(2024, 12, 25));
        inputDto.setTeamId(5L);

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(userRepository.getReferenceById(1L)).thenReturn(whiteUser);
        when(userRepository.getReferenceById(2L)).thenReturn(blackUser);
        when(teamRepository.findById(5L)).thenReturn(Optional.of(team));
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Updated Championship", result.getEvent());
        assertEquals("New York", result.getSite());
        assertEquals("0-1", result.getResult());
        assertEquals(7, result.getRound());
        assertEquals("1. d4 d5 2. c4 e6", result.getMoves());
        assertEquals("Queen's Gambit", result.getOpening());
        assertEquals("Board 2", result.getBoard());
        assertEquals(LocalDate.of(2024, 12, 25), result.getDate());
        assertEquals(5L, result.getTeamId());
        assertEquals("Team Alpha", result.getTeamName());

        // Verify player mapping
        assertNotNull(result.getWhitePlayer());
        assertEquals(1L, result.getWhitePlayer().getId());
        assertEquals("Magnus", result.getWhitePlayer().getFirstName());
        assertEquals("Carlsen", result.getWhitePlayer().getLastName());

        assertNotNull(result.getBlackPlayer());
        assertEquals(2L, result.getBlackPlayer().getId());
        assertEquals("Ian", result.getBlackPlayer().getFirstName());
        assertEquals("Nepomniachtchi", result.getBlackPlayer().getLastName());

        verify(gameRepository).findById(10L);
        verify(gameRepository).save(any(Game.class));
    }

    @Test
    public void updateDate_WithUnregisteredPlayers_ReturnsUpdatedGame() {
        // Given – players without IDs (plain text names only)
        GamePlayer unregWhite = new GamePlayer();
        unregWhite.setFirstName("Bobby");
        unregWhite.setLastName("Fischer");

        GamePlayer unregBlack = new GamePlayer();
        unregBlack.setFirstName("Boris");
        unregBlack.setLastName("Spassky");

        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setWhitePlayer(unregWhite);
        inputDto.setBlackPlayer(unregBlack);
        inputDto.setEvent("Match of the Century");
        inputDto.setMoves("1. e4 c5");
        inputDto.setResult("1-0");

        // Build expected saved entity: no user references, only plain text names
        Game savedEntity = Game.builder()
                .id(10L)
                .event("Match of the Century")
                .moves("1. e4 c5")
                .result("1-0")
                .round(0)
                .white_user(null)
                .white_player_name(null)
                .black_player_name("Boris Spassky")
                .black_user(null)
                .build();

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game saved = invocation.getArgument(0);
            // Simulate that entity is saved and returned as-is
            return saved;
        });

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Match of the Century", result.getEvent());
        assertEquals("1-0", result.getResult());

        // White player has no ID -> mapUnregisteredNameToGamePlayer is used
        // In mapAndSave: whitePlayer is non-null but id is null -> white_user=null, white_player_name=null
        // This is the current behavior of mapAndSave
        verify(gameRepository).findById(10L);
        verify(gameRepository).save(any(Game.class));
        verify(userRepository, never()).getReferenceById(anyLong());
    }

    @Test
    public void updateDate_WithNullPlayers_ReturnsUpdatedGame() {
        // Given – no players set at all
        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setMoves("1. e4 e5");
        inputDto.setResult("1/2-1/2");

        Game savedEntity = Game.builder()
                .id(10L)
                .moves("1. e4 e5")
                .result("1/2-1/2")
                .round(0)
                .white_user(null)
                .white_player_name(null)
                .black_user(null)
                .black_player_name(null)
                .build();

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Game.class))).thenReturn(savedEntity);

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertNull(result.getWhitePlayer());
        assertNull(result.getBlackPlayer());
        assertEquals("1/2-1/2", result.getResult());
        verify(gameRepository).save(any(Game.class));
    }

    @Test
    public void updateDate_WithoutTeam_ReturnsGameWithoutTeam() {
        // Given
        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setMoves("1. e4 e5");

        Game savedEntity = Game.builder()
                .id(10L)
                .moves("1. e4 e5")
                .round(0)
                .build();

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Game.class))).thenReturn(savedEntity);

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertNull(result.getTeamId());
        assertNull(result.getTeamName());
        verify(teamRepository, never()).findById(anyLong());
    }

    @Test
    public void updateDate_WithNullDate_DateIsNotSet() {
        // Given
        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setDate(null);
        inputDto.setMoves("1. e4 e5");

        Game savedEntity = Game.builder()
                .id(10L)
                .moves("1. e4 e5")
                .round(0)
                .date(null)
                .build();

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Game.class))).thenReturn(savedEntity);

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertNull(result.getDate());
    }

    @Test
    public void updateDate_WithNullRound_RoundIsSetToZeroAndMappedToNull() {
        // Given – round is null in the DTO, mapAndSave converts it to 0,
        // mapToDto converts 0 back to null
        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setRound(null);
        inputDto.setMoves("1. e4 e5");

        Game savedEntity = Game.builder()
                .id(10L)
                .moves("1. e4 e5")
                .round(0)
                .build();

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(Game.class))).thenReturn(savedEntity);

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertNull(result.getRound()); // round=0 in entity maps to null in DTO
    }

    // =====================================================================
    // updateDate() – Error / Edge Case Tests
    // =====================================================================

    @Test
    public void updateDate_GameNotFound_ThrowsResourceNotFoundException() {
        // Given
        GameDto inputDto = new GameDto();
        inputDto.setId(999L);

        when(gameRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gameService.updateDate(inputDto)
        );

        assertTrue(exception.getMessage().contains("Game"));
        assertTrue(exception.getMessage().contains("999"));
        verify(gameRepository).findById(999L);
        verify(gameRepository, never()).save(any(Game.class));
    }

    @Test
    public void updateDate_TeamNotFound_ThrowsResourceNotFoundException() {
        // Given
        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setTeamId(999L);
        inputDto.setMoves("1. e4 e5");

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> gameService.updateDate(inputDto)
        );

        assertTrue(exception.getMessage().contains("Team"));
        assertTrue(exception.getMessage().contains("999"));
        verify(gameRepository, never()).save(any(Game.class));
    }

    @Test
    public void updateDate_VerifiesGameIsSavedWithCorrectId() {
        // Given – ensures the ID from the DTO is preserved during save
        GameDto inputDto = new GameDto();
        inputDto.setId(42L);
        inputDto.setMoves("1. Nf3 Nf6");

        Game existingGameForId42 = Game.builder().id(42L).moves("old moves").round(0).build();

        when(gameRepository.findById(42L)).thenReturn(Optional.of(existingGameForId42));
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game saved = invocation.getArgument(0);
            assertEquals(42L, saved.getId(), "The saved Game entity must keep the original ID");
            return saved;
        });

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        assertEquals(42L, result.getId());
        verify(gameRepository).save(any(Game.class));
    }

    @Test
    public void updateDate_AllFieldsArePersisted() {
        // Given – full DTO with every field set
        GamePlayer whitePlayerDto = new GamePlayer();
        whitePlayerDto.setId(1L);
        whitePlayerDto.setFirstName("Magnus");
        whitePlayerDto.setLastName("Carlsen");

        GamePlayer blackPlayerDto = new GamePlayer();
        blackPlayerDto.setId(2L);
        blackPlayerDto.setFirstName("Ian");
        blackPlayerDto.setLastName("Nepomniachtchi");

        GameDto inputDto = new GameDto();
        inputDto.setId(10L);
        inputDto.setWhitePlayer(whitePlayerDto);
        inputDto.setBlackPlayer(blackPlayerDto);
        inputDto.setEvent("Candidates 2024");
        inputDto.setSite("Toronto");
        inputDto.setResult("0-1");
        inputDto.setRound(14);
        inputDto.setMoves("1. d4 Nf6 2. c4 e6");
        inputDto.setOpening("Nimzo-Indian");
        inputDto.setBoard("Board 3");
        inputDto.setDate(LocalDate.of(2024, 4, 20));
        inputDto.setTeamId(5L);

        when(gameRepository.findById(10L)).thenReturn(Optional.of(existingGame));
        when(userRepository.getReferenceById(1L)).thenReturn(whiteUser);
        when(userRepository.getReferenceById(2L)).thenReturn(blackUser);
        when(teamRepository.findById(5L)).thenReturn(Optional.of(team));
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game saved = invocation.getArgument(0);
            // Verify all fields on the entity before it's saved
            assertEquals(10L, saved.getId());
            assertEquals("Candidates 2024", saved.getEvent());
            assertEquals("Toronto", saved.getSite());
            assertEquals("0-1", saved.getResult());
            assertEquals(14, saved.getRound());
            assertEquals("1. d4 Nf6 2. c4 e6", saved.getMoves());
            assertEquals("Nimzo-Indian", saved.getOpening());
            assertEquals("Board 3", saved.getBoard());
            assertEquals(whiteUser, saved.getWhite_user());
            assertEquals(blackUser, saved.getBlack_user());
            assertEquals(team, saved.getTeam());
            assertNotNull(saved.getDate());
            return saved;
        });

        // When
        GameDto result = gameService.updateDate(inputDto);

        // Then
        assertNotNull(result);
        verify(gameRepository).save(any(Game.class));
    }
}

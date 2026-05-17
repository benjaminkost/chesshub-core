package de.ben_kostka.chesshub_core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.ben_kostka.chesshub_core.api.dto.GameDto;
import de.ben_kostka.chesshub_core.api.dto.GamePlayer;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(GameController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private ObjectMapper objectMapper;
    private GameDto inputGameDto;
    private GameDto returnedGameDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        GamePlayer whitePlayer = new GamePlayer();
        whitePlayer.setId(1L);
        whitePlayer.setFirstName("Magnus");
        whitePlayer.setLastName("Carlsen");

        GamePlayer blackPlayer = new GamePlayer();
        blackPlayer.setId(2L);
        blackPlayer.setFirstName("Ian");
        blackPlayer.setLastName("Nepomniachtchi");

        inputGameDto = new GameDto();
        inputGameDto.setId(10L);
        inputGameDto.setWhitePlayer(whitePlayer);
        inputGameDto.setBlackPlayer(blackPlayer);
        inputGameDto.setEvent("World Championship 2024");
        inputGameDto.setSite("Singapore");
        inputGameDto.setDate(LocalDate.of(2024, 12, 1));
        inputGameDto.setRound(5);
        inputGameDto.setResult("1-0");
        inputGameDto.setMoves("1. e4 e5 2. Nf3 Nc6");
        inputGameDto.setOpening("Italian Game");
        inputGameDto.setBoard("Board 1");
        inputGameDto.setTeamId(3L);

        // The service returns the same DTO with a teamName populated
        returnedGameDto = new GameDto();
        returnedGameDto.setId(10L);
        returnedGameDto.setWhitePlayer(whitePlayer);
        returnedGameDto.setBlackPlayer(blackPlayer);
        returnedGameDto.setEvent("World Championship 2024");
        returnedGameDto.setSite("Singapore");
        returnedGameDto.setDate(LocalDate.of(2024, 12, 1));
        returnedGameDto.setRound(5);
        returnedGameDto.setResult("1-0");
        returnedGameDto.setMoves("1. e4 e5 2. Nf3 Nc6");
        returnedGameDto.setOpening("Italian Game");
        returnedGameDto.setBoard("Board 1");
        returnedGameDto.setTeamId(3L);
        returnedGameDto.setTeamName("Team Alpha");
    }

    @Test
    public void updateGame_ReturnsOk() throws Exception {
        // Given
        when(gameService.updateDate(any(GameDto.class))).thenReturn(returnedGameDto);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputGameDto)));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(returnedGameDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.event").value(returnedGameDto.getEvent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.site").value(returnedGameDto.getSite()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(returnedGameDto.getResult()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.round").value(returnedGameDto.getRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.moves").value(returnedGameDto.getMoves()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.opening").value(returnedGameDto.getOpening()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board").value(returnedGameDto.getBoard()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teamId").value(returnedGameDto.getTeamId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teamName").value(returnedGameDto.getTeamName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer.firstName").value("Magnus"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer.lastName").value("Carlsen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blackPlayer.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blackPlayer.firstName").value("Ian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blackPlayer.lastName").value("Nepomniachtchi"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateGame_WithMinimalData_ReturnsOk() throws Exception {
        // Given – only the required field (id) and moves
        GameDto minimalInput = new GameDto();
        minimalInput.setId(20L);
        minimalInput.setMoves("1. d4 d5");

        GameDto minimalReturned = new GameDto();
        minimalReturned.setId(20L);
        minimalReturned.setMoves("1. d4 d5");

        when(gameService.updateDate(any(GameDto.class))).thenReturn(minimalReturned);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(minimalInput)));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.moves").value("1. d4 d5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.blackPlayer").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.teamId").doesNotExist())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateGame_GameNotFound_Returns404() throws Exception {
        // Given
        when(gameService.updateDate(any(GameDto.class)))
                .thenThrow(new ResourceNotFoundException("Game", "id", "999"));

        GameDto notFoundInput = new GameDto();
        notFoundInput.setId(999L);
        notFoundInput.setMoves("1. e4 e5");

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notFoundInput)));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateGame_WithUnregisteredPlayers_ReturnsOk() throws Exception {
        // Given – players without IDs (plain text names)
        GamePlayer unregisteredWhite = new GamePlayer();
        unregisteredWhite.setFirstName("Bobby");
        unregisteredWhite.setLastName("Fischer");

        GamePlayer unregisteredBlack = new GamePlayer();
        unregisteredBlack.setFirstName("Boris");
        unregisteredBlack.setLastName("Spassky");

        GameDto unregInput = new GameDto();
        unregInput.setId(30L);
        unregInput.setWhitePlayer(unregisteredWhite);
        unregInput.setBlackPlayer(unregisteredBlack);
        unregInput.setMoves("1. e4 e5");
        unregInput.setResult("1-0");

        GameDto unregReturned = new GameDto();
        unregReturned.setId(30L);
        unregReturned.setWhitePlayer(unregisteredWhite);
        unregReturned.setBlackPlayer(unregisteredBlack);
        unregReturned.setMoves("1. e4 e5");
        unregReturned.setResult("1-0");

        when(gameService.updateDate(any(GameDto.class))).thenReturn(unregReturned);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unregInput)));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer.id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer.firstName").value("Bobby"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.whitePlayer.lastName").value("Fischer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blackPlayer.firstName").value("Boris"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blackPlayer.lastName").value("Spassky"))
                .andDo(MockMvcResultHandlers.print());
    }
}

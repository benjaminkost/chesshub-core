package de.ben_kostka.benchesster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ben_kostka.benchesster.model.User;
import de.ben_kostka.benchesster.payload.ClubDto;
import de.ben_kostka.benchesster.service.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

// Resource: https://www.youtube.com/watch?v=Sixeh7zjtOY

@WebMvcTest(ClubController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClubControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClubService clubService;  // Service wird gemockt

    @Autowired
    private ObjectMapper objectMapper;  // JSON <-> Object

    private ClubDto clubDto;

    private List<ClubDto> clubDtoList;

    @BeforeEach
    void setUp() {
        // Create single ClubDto
        clubDto = new ClubDto();
        clubDto.setId(1L);
        clubDto.setName("testClubName");
        User user = new User();
        user.setId(1L);
        user.setUsername("testPresidentName");
        user.setPassword("testPresidentPassword");
        clubDto.setPresident(user);

        // Create all ClubDto's to mock what ClubDto's are in the DB
        ClubDto clubDto2 = new ClubDto();
        clubDto2.setId(2L);
        clubDto2.setName("testClubName2");
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testPresidentName2");
        user2.setPassword("testPresidentPassword2");
        clubDto2.setPresident(user2);
        clubDtoList = List.of(clubDto, clubDto2);
    }

    @Test
    public void create_correctClubDto_ReturnsCreated() throws Exception {
        // Give
        given(clubService.createClub(ArgumentMatchers.any())) //define what the Serivice will give back
                .willAnswer(invocation -> invocation.getArgument(0)); // gives back the first Input-Value

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/clubs")
                .contentType(MediaType.APPLICATION_JSON) //
                .content(objectMapper.writeValueAsString(clubDto)));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clubDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(clubDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.president.id").value(clubDto.getPresident().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.president.username").value(clubDto.getPresident().getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.president.password").value(clubDto.getPresident().getPassword()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllClubs_noInput_ReturnsCreated() throws Exception {
        // Give
        when(clubService.getAllClubDto()).thenReturn(clubDtoList);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/clubs")
        .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(clubDtoList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(clubDtoList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].president.id").value(clubDtoList.get(0).getPresident().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].president.username").value(clubDtoList.get(0).getPresident().getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].president.password").value(clubDtoList.get(0).getPresident().getPassword()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(clubDtoList.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value(clubDtoList.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].president.id").value(clubDtoList.get(1).getPresident().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].president.username").value(clubDtoList.get(1).getPresident().getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].president.password").value(clubDtoList.get(1).getPresident().getPassword()))
                .andDo(MockMvcResultHandlers.print());
    }
}

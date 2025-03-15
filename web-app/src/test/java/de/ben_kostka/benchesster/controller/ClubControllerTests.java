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
import static org.mockito.BDDMockito.given;

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

    @BeforeEach
    void setUp() {
        clubDto = new ClubDto();
        clubDto.setId(1L);
        clubDto.setName("testClubName");
        User user = new User();
        user.setId(1L);
        user.setUsername("testPresidentName");
        user.setPassword("testPresidentPassword");
        clubDto.setPresident(user);
    }

    @Test
    public void create_correctClubDto_ReturnsCreated() throws Exception {
        // Give
        given(clubService.createClub(ArgumentMatchers.any())) //define what the Serivice will give back
                .willAnswer(invocation -> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clubDto)));

        // then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testClubName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.president.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.president.username").value("testPresidentName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.president.password").value("testPresidentPassword"))
                .andDo(MockMvcResultHandlers.print());
    }
}

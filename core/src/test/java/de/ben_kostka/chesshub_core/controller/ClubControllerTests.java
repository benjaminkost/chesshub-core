package de.ben_kostka.chesshub_core.controller;

import de.ben_kostka.chesshub_core.api.dto.Club;
import de.ben_kostka.chesshub_core.api.dto.ClubSimple;
import de.ben_kostka.chesshub_core.service.ClubService;
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

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ClubController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClubControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClubService clubService;

    private List<ClubSimple> clubSimpleList;
    private Club clubFull;

    @BeforeEach
    void setUp() {
        ClubSimple clubSimple1 = new ClubSimple();
        clubSimple1.setId(1L);
        clubSimple1.setName("testClubName");
        clubSimple1.setAdminId(1L);

        ClubSimple clubSimple2 = new ClubSimple();
        clubSimple2.setId(2L);
        clubSimple2.setName("testClubName2");
        clubSimple2.setAdminId(2L);

        clubSimpleList = List.of(clubSimple1, clubSimple2);
        
        clubFull = new Club();
        clubFull.setId(1L);
        clubFull.setName("testClubName");
        clubFull.setAddress("Test Address 123");
    }

    @Test
    public void getAllClubs_ReturnsOk() throws Exception {
        // Give
        when(clubService.getAllClubs()).thenReturn(clubSimpleList);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/clubs")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(clubSimpleList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(clubSimpleList.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].adminId").value(clubSimpleList.get(0).getAdminId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(clubSimpleList.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value(clubSimpleList.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].adminId").value(clubSimpleList.get(1).getAdminId()))
                .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void getClubById_ReturnsOk() throws Exception {
        // Give
        when(clubService.getClubById(1L)).thenReturn(clubFull);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/clubs/1")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clubFull.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(clubFull.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(clubFull.getAddress()))
                .andDo(MockMvcResultHandlers.print());
    }
}

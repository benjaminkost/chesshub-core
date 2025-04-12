package de.ben_kostka.benchesster.controller;

import com.github.javafaker.Faker;
import de.ben_kostka.benchesster.payload.AuthResponseDto;
import de.ben_kostka.benchesster.payload.LoginDto;
import de.ben_kostka.benchesster.payload.RegisterDto;
import de.ben_kostka.benchesster.service.AuthService;
import org.junit.jupiter.api.BeforeAll;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    static Faker faker;

    @BeforeAll
    public static void setup() {
        faker = new Faker();
    }

    @Test
    public void register_CorrectUser_ReturnsUser() throws Exception {
        // Give
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName(faker.name().firstName());
        registerDto.setLastName(faker.name().lastName());
        registerDto.setUsername(faker.name().username());
        registerDto.setEmail(faker.internet().emailAddress());
        registerDto.setPassword(faker.internet().password());

        given(authService.register(ArgumentMatchers.any(RegisterDto.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)));

        // Then
        response.andExpectAll(
                MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.firstName").value(registerDto.getFirstName()),
                MockMvcResultMatchers.jsonPath("$.lastName").value(registerDto.getLastName()),
                MockMvcResultMatchers.jsonPath("$.username").value(registerDto.getUsername()),
                MockMvcResultMatchers.jsonPath("$.email").value(registerDto.getEmail())
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void login_CorrectUser_ReturnsUser() throws Exception {
        // Give
        LoginDto logindto = new LoginDto();
        logindto.setUsernameOrEmail(faker.internet().emailAddress());
        logindto.setPassword(faker.internet().password());

        AuthResponseDto authResponseDto = new AuthResponseDto(faker.name().firstName());

        given(authService.login(ArgumentMatchers.any(LoginDto.class)))
            .willReturn(authResponseDto);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logindto)));

        // Then
        response.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.accessToken").value(authResponseDto.getAccessToken())
        );
    }
}

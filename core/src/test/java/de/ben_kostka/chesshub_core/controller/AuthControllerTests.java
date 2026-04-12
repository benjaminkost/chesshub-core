package de.ben_kostka.chesshub_core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import de.ben_kostka.chesshub_core.api.dto.LoginRequest;
import de.ben_kostka.chesshub_core.api.dto.RegisterRequest;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;
import de.ben_kostka.chesshub_core.service.AuthService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
        RegisterRequest registerDto = new RegisterRequest();
        registerDto.setFirstName(faker.name().firstName());
        registerDto.setLastName(faker.name().lastName());
        registerDto.setUserName(faker.name().username());
        registerDto.setUserName(faker.name().username());
        registerDto.setEmail(faker.internet().emailAddress());
        registerDto.setPassword(faker.internet().password());

        UserSimple userSimple = new UserSimple();
        userSimple.setId(1L);
        userSimple.setName(registerDto.getFirstName()+" "+registerDto.getLastName());
        userSimple.setUserName(registerDto.getUserName());

        given(authService.register(ArgumentMatchers.any(RegisterRequest.class)))
                .willAnswer(invocation -> userSimple);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)));

        // Then
        response.andExpectAll(
                MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.jsonPath("$.id").value(userSimple.getId()),
                MockMvcResultMatchers.jsonPath("$.name").value(userSimple.getName()),
                MockMvcResultMatchers.jsonPath("$.userName").value(userSimple.getUserName())
        ).andDo(print());
    }

    @Test
    public void login_CorrectUser_ReturnsUser() throws Exception {
        // Give
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsernameOrEmail(faker.internet().emailAddress());
        loginRequest.setPassword(faker.internet().password());

        UserSimple userSimple = new UserSimple();
        userSimple.setId(1L);
        userSimple.setName(faker.name().fullName());
        userSimple.setUserName(faker.name().username());

        AuthService.AuthResult authResult = new AuthService.AuthResult("fake-jwt-token", userSimple);

        given(authService.login(ArgumentMatchers.any(LoginRequest.class)))
            .willReturn(authResult);

        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));

        // Then
        response.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.cookie().exists("token"),
                MockMvcResultMatchers.cookie().httpOnly("token", true),
                MockMvcResultMatchers.jsonPath("$.id").value(userSimple.getId()),
                MockMvcResultMatchers.jsonPath("$.userName").value(userSimple.getUserName()),
                MockMvcResultMatchers.jsonPath("$.name").value(userSimple.getName())
        ).andDo(print());
    }
}

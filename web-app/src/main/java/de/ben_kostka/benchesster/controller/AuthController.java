package de.ben_kostka.benchesster.controller;

import de.ben_kostka.benchesster.payload.AuthResponseDto;
import de.ben_kostka.benchesster.payload.LoginDto;
import de.ben_kostka.benchesster.payload.RegisterDto;
import de.ben_kostka.benchesster.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto){
        RegisterDto response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        AuthResponseDto response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.payload.AuthResponseDto;
import de.ben_kostka.chesshub_core.payload.LoginDto;
import de.ben_kostka.chesshub_core.payload.RegisterDto;

public interface AuthService {
    AuthResponseDto login(LoginDto loginDto);

    RegisterDto register(RegisterDto registerDto);
}

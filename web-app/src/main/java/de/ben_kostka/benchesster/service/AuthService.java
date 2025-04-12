package de.ben_kostka.benchesster.service;

import de.ben_kostka.benchesster.payload.AuthResponseDto;
import de.ben_kostka.benchesster.payload.LoginDto;
import de.ben_kostka.benchesster.payload.RegisterDto;

public interface AuthService {
    AuthResponseDto login(LoginDto loginDto);

    RegisterDto register(RegisterDto registerDto);
}

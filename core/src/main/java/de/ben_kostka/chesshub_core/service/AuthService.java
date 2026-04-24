package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.LoginRequest;
import de.ben_kostka.chesshub_core.api.dto.RegisterRequest;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;

public interface AuthService {
    record AuthResult(String token, UserSimple user) {}

    AuthResult login(LoginRequest loginDto);

    UserSimple register(RegisterRequest registerDto);
}

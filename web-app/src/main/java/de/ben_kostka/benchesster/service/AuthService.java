package de.ben_kostka.benchesster.service;

import de.ben_kostka.benchesster.payload.LoginDto;
import de.ben_kostka.benchesster.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}

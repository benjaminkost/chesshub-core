package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.ClubAffiliation;
import de.ben_kostka.chesshub_core.api.dto.UserResponse;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;

import java.util.List;

public interface UserService {
    List<UserSimple> getAllUsers();
    UserResponse getCurrentUser();
    List<ClubAffiliation> getMyClubs();
}

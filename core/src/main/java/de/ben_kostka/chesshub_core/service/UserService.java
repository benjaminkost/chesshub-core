package de.ben_kostka.chesshub_core.service;

import de.ben_kostka.chesshub_core.api.dto.ClubAffiliation;
import de.ben_kostka.chesshub_core.api.dto.User;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;

import java.util.List;

public interface UserService {
    List<UserSimple> getAllUsers();
    User getCurrentUser();
    List<ClubAffiliation> getMyClubs();
}

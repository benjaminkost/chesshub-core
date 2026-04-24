package de.ben_kostka.chesshub_core.controller;

import de.ben_kostka.chesshub_core.api.UsersApi;
import de.ben_kostka.chesshub_core.api.dto.ClubAffiliation;
import de.ben_kostka.chesshub_core.api.dto.UserResponse;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;
import de.ben_kostka.chesshub_core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UsersApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserSimple>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @Override
    public ResponseEntity<List<ClubAffiliation>> getMyClubs() {
        return ResponseEntity.ok(userService.getMyClubs());
    }
}

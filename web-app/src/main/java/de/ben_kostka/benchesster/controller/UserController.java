package de.ben_kostka.benchesster.controller;

import de.ben_kostka.benchesster.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public ResponseEntity<User> createUser(User user) {
        return null;
    }

    public ResponseEntity<User> createUsersWithListInput(List<@Valid User> user) {
        return null;
    }

    public ResponseEntity<Void> deleteUser(int user_ID) {
        return null;
    }

    public ResponseEntity<User> getUserByName(int user_ID) {
        return null;
    }

    public ResponseEntity<String> loginUser(String username, String password) {
        return null;
    }

    public ResponseEntity<Void> logoutUser() {
        return null;
    }

    public ResponseEntity<Void> updateUser(int user_ID, User user) {
        return null;
    }
}

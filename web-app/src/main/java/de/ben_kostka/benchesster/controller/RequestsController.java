package de.ben_kostka.benchesster.controller;

import de.ben_kostka.benchesster.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestsController {

    public ResponseEntity<User> getRequestedGames(String user_ID) {
        return null;
    }
}

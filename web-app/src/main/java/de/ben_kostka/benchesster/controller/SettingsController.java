package de.ben_kostka.benchesster.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    public ResponseEntity<String> getSettingsoptions() {
        return null;
    }
}

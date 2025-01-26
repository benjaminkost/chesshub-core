package de.ben_kostka.benchesster.controller;



import de.ben_kostka.benchesster.api.SettingsApi;
import de.ben_kostka.benchesster.delegate.SettingsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingsApiController implements SettingsApi {

    private final SettingsApiDelegate delegate;

    public SettingsApiController(@Autowired(required = false) SettingsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SettingsApiDelegate() {});
    }

    @Override
    public SettingsApiDelegate getDelegate() {
        return delegate;
    }

}

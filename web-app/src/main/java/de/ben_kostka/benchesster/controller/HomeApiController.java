package de.ben_kostka.benchesster.controller;



import de.ben_kostka.benchesster.api.HomeApi;
import de.ben_kostka.benchesster.delegate.HomeApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeApiController implements HomeApi {

    private final HomeApiDelegate delegate;

    public HomeApiController(@Autowired(required = false) HomeApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new HomeApiDelegate() {});
    }

    @Override
    public HomeApiDelegate getDelegate() {
        return delegate;
    }

}

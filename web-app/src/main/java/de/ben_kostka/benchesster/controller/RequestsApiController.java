package de.ben_kostka.benchesster.controller;

import de.ben_kostka.benchesster.api.RequestsApi;


import de.ben_kostka.benchesster.delegate.RequestsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestsApiController implements RequestsApi {

    private final RequestsApiDelegate delegate;

    public RequestsApiController(@Autowired(required = false) RequestsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RequestsApiDelegate() {});
    }

    @Override
    public RequestsApiDelegate getDelegate() {
        return delegate;
    }

}

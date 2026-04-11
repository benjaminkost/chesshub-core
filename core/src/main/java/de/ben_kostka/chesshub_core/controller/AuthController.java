package de.ben_kostka.chesshub_core.controller;

import de.ben_kostka.chesshub_core.api.AuthApi;
import de.ben_kostka.chesshub_core.api.dto.LoginRequest;
import de.ben_kostka.chesshub_core.api.dto.RegisterRequest;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;
import de.ben_kostka.chesshub_core.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class AuthController implements AuthApi {
    
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<UserSimple> login(LoginRequest loginRequest) {
        AuthService.AuthResult result = authService.login(loginRequest);
        
        // Create the HttpOnly cookie for the token
        Cookie cookie = new Cookie("token", result.token());
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Make true in production over HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (response != null) {
            response.addCookie(cookie);
        }
        
        return new ResponseEntity<>(result.user(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logout() {
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Delete cookie
        
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (response != null) {
            response.addCookie(cookie);
        }
        
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserSimple> register(RegisterRequest registerRequest) {
        UserSimple response = authService.register(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

package de.ben_kostka.chesshub_core.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecurityConstants {
    @Value("${security.jwt.expiration}")
    private long jwtExpiration;

}
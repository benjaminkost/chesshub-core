package de.ben_kostka.chesshub_core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    @Value("${security.jwt.expiration:86400000}")
    private long jwtExpiration;

    public long getJwtExpiration() {
        return jwtExpiration;
    }
}
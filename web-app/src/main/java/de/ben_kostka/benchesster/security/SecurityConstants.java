package de.ben_kostka.benchesster.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    @Value("${security.jwt.expiration}")
    public static long JWT_EXPIRATION;

}
package de.ben_kostka.benchesster.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails ben = User.builder().username("ben").password("{noop}password").roles("ADMIN", "USER", "CLUB_LEADER").build();

        UserDetails lukas = User.builder().username("lukas").password("{noop}password").roles("ADMIN", "USER", "CLUB_LEADER").build();

        UserDetails filip = User.builder().username("filip").password("{noop}password").roles("USER").build();

        return new InMemoryUserDetailsManager(ben, lukas, filip);
    }
}

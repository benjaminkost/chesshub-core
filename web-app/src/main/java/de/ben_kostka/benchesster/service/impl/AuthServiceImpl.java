package de.ben_kostka.benchesster.service.impl;

import de.ben_kostka.benchesster.exception.APIException;
import de.ben_kostka.benchesster.model.Role;
import de.ben_kostka.benchesster.model.User;
import de.ben_kostka.benchesster.payload.LoginDto;
import de.ben_kostka.benchesster.payload.RegisterDto;
import de.ben_kostka.benchesster.repository.RoleRepository;
import de.ben_kostka.benchesster.repository.UserRepository;
import de.ben_kostka.benchesster.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User Logged-in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        // add check for username exist in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = null;

        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
            userRole = roleRepository.findByName("ROLE_USER").get();
        }else{
            roleRepository.findByName("ROLE_USER").get(); // muss überarbeitet werden werden
        }
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }
}

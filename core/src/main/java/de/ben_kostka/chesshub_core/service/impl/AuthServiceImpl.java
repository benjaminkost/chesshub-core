package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.exception.APIException;
import de.ben_kostka.chesshub_core.model.Role;
import de.ben_kostka.chesshub_core.model.User;
import de.ben_kostka.chesshub_core.api.dto.LoginRequest;
import de.ben_kostka.chesshub_core.api.dto.RegisterRequest;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;
import de.ben_kostka.chesshub_core.repository.RoleRepository;
import de.ben_kostka.chesshub_core.repository.UserRepository;
import de.ben_kostka.chesshub_core.security.JWTGenerator;
import de.ben_kostka.chesshub_core.service.AuthService;
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

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public AuthResult login(LoginRequest loginDto) {
        Authentication authentication  = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        
        User userEntity = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "User not found"));
                
        UserSimple userSimple = new UserSimple();
        userSimple.setId(userEntity.getId());
        userSimple.setName(userEntity.getFirstName() + " " + userEntity.getLastName());
        userSimple.setUserName(userEntity.getUsername());

        return new AuthResult(token, userSimple);
    }

    @Override
    public UserSimple register(RegisterRequest registerDto) {
        if (userRepository.existsByUsername(registerDto.getUserName())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhoneNumber()); // Note: using phoneNumber from RegisterRequest
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role role = new Role();
            role.setName("ROLE_USER");
            return roleRepository.save(role);
        });
        roles.add(userRole);
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        UserSimple userSimple = new UserSimple();
        userSimple.setId(savedUser.getId());
        userSimple.setName(savedUser.getFirstName() + " " + savedUser.getLastName());
        userSimple.setUserName(savedUser.getUsername());
        return userSimple;
    }
}

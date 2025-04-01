package de.ben_kostka.benchesster.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.benchesster.AbstractTestcontainers;
import de.ben_kostka.benchesster.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIT extends AbstractTestcontainers {

    @Autowired
    private UserRepository underTest;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        // Create User
        Faker faker = new Faker();

        User testUser = new User();
        testUser.setUsername(faker.name().username());
        testUser.setFirstName(faker.name().firstName());
        testUser.setLastName(faker.name().lastName());
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword(faker.internet().password());

        Team testTeam = new Team();
        testTeam.setName("Team 1");

        testUser.setTeams(new HashSet<>(Set.of(testTeam)));

        Role testRole = new Role();
        testRole.setName("Role 1");
        testUser.setRoles(new HashSet<>(Set.of(testRole)));

        // Save game
        underTest.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        underTest.deleteAll();
        teamRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldCreateNewUser(){
        // Give
        Faker faker = new Faker();

        User testUser = new User();
        testUser.setUsername(faker.name().username());
        testUser.setFirstName(faker.name().firstName());
        testUser.setLastName(faker.name().lastName());
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword(faker.internet().password());

        Team testTeam = new Team();
        testTeam.setName("Team 1");

        testUser.setTeams(new HashSet<>(Set.of(testTeam)));

        Role testRole = new Role();
        testRole.setName("Role 1");
        testUser.setRoles(new HashSet<>(Set.of(testRole)));

        // When
        underTest.save(testUser);
        User savedUser = underTest.findById(testUser.getId()).get();

        // Then
        Assertions.assertEquals(testUser.getUsername(), savedUser.getUsername());
        Assertions.assertEquals(testUser.getFirstName(), savedUser.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), savedUser.getLastName());
        Assertions.assertEquals(testUser.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(testUser.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(testUser.getRoles(), savedUser.getRoles());
        Assertions.assertEquals(testUser.getTeams(), savedUser.getTeams());
    }

    @Test
    public void save_changeRoles_ShouldUpdateUser() {
        // Give
        User testUser = underTest.findAll().stream().findFirst().get();

        Role testRole = new Role();
        testRole.setName("Role 2");

        testUser.setRoles(new HashSet<>(Set.of(testRole)));

        // When
        underTest.save(testUser);
        User savedUser = underTest.findById(testUser.getId()).get();

        // Then
        Assertions.assertEquals(testUser.getUsername(), savedUser.getUsername());
        Assertions.assertEquals(testUser.getFirstName(), savedUser.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), savedUser.getLastName());
        Assertions.assertEquals(testUser.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(testUser.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(testUser.getRoles(), savedUser.getRoles());

    }

    @Test
    public void save_changeTeams_ShouldUpdateUser() {
        // Give

        // When
        underTest.deleteAll();

        List<User> savedGame = underTest.findAll();
        List<Team> savedTeams = teamRepository.findAll();
        List<Role> savedRoles = roleRepository.findAll();

        // Then
        Assertions.assertEquals(0, savedGame.size());
        Assertions.assertEquals(1, savedTeams.size());
        Assertions.assertEquals(1, savedRoles.size());
    }


}

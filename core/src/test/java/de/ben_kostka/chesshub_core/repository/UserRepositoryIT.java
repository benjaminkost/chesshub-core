package de.ben_kostka.chesshub_core.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.chesshub_core.AbstractTestcontainers;
import de.ben_kostka.chesshub_core.model.*;
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
    private TeamMembershipRepository teamMembership;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        underTest.deleteAll();
        teamMembership.deleteAll();
        teamRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldCreateNewUser(){
        // Give
        Faker faker = new Faker();

        User testUser = User.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        underTest.save(testUser);

        Team testTeam = Team.builder()
                .name("Team 1")
                .build();
        teamRepository.save(testTeam);

        Role testRole = new Role();
        testRole.setName("Role 1");
        roleRepository.save(testRole);

        testUser.setRoles(new HashSet<>(Set.of(testRole)));

        TeamMembership tm = TeamMembership.builder()
                .id(new TeamMembershipId(testUser.getId(), testTeam.getId()))
                .user(testUser)
                .team(testTeam)
                .roles("MEMBER")
                .build();
        
        testUser.getTeamMemberships().add(tm);

        // When
        underTest.save(testUser);
        User savedUser = underTest.findById(testUser.getId()).get();

        // Then
        Assertions.assertEquals(testUser.getUsername(), savedUser.getUsername());
        Assertions.assertEquals(testUser.getFirstName(), savedUser.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), savedUser.getLastName());
        Assertions.assertEquals(testUser.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(testUser.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(1, savedUser.getRoles().size());
        Assertions.assertEquals(1, savedUser.getTeamMemberships().size());
    }

    @Test
    public void save_changeRoles_ShouldUpdateUser() {
        // Give
        Faker faker = new Faker();
        User testUser = User.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .build();
        underTest.save(testUser);

        Role testRole = new Role();
        testRole.setName("Role 2");
        roleRepository.save(testRole);

        testUser.setRoles(new HashSet<>(Set.of(testRole)));

        // When
        underTest.save(testUser);
        User savedUser = underTest.findById(testUser.getId()).get();

        // Then
        Assertions.assertEquals(1, savedUser.getRoles().size());
        Assertions.assertTrue(savedUser.getRoles().contains(testRole));
    }

    @Test
    public void deleteAll_ShouldClearRepositories() {
        // When
        underTest.deleteAll();

        List<User> users = underTest.findAll();
        // Then
        Assertions.assertEquals(0, users.size());
    }

    @Test
    public void deleteAll_ShouldNotDeleteTeamsAndRoles() {
        // Given
        Faker faker = new Faker();

        User testUser = User.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        underTest.save(testUser);

        Team testTeam = Team.builder()
                .name("Team 1")
                .build();
        teamRepository.save(testTeam);

        Role testRole = new Role();
        testRole.setName("Role 1");
        roleRepository.save(testRole);

        testUser.setRoles(new HashSet<>(Set.of(testRole)));

        TeamMembership tm = TeamMembership.builder()
                .id(new TeamMembershipId(testUser.getId(), testTeam.getId()))
                .user(testUser)
                .team(testTeam)
                .roles("MEMBER")
                .build();

        testUser.getTeamMemberships().add(tm);

        underTest.save(testUser);

        // When
        underTest.deleteAll();

        List<User> users = underTest.findAll();
        List<Team> teams = teamRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        // Then
        Assertions.assertEquals(0, users.size());
        Assertions.assertEquals(1, teams.size());
        Assertions.assertEquals(1, roles.size());
    }
}

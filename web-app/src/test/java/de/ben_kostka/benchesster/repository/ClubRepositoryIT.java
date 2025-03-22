package de.ben_kostka.benchesster.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.benchesster.AbstractTestcontainers;
import de.ben_kostka.benchesster.model.Club;
import de.ben_kostka.benchesster.model.Role;
import de.ben_kostka.benchesster.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClubRepositoryIT extends AbstractTestcontainers {

    @Autowired
    private ClubRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldCreateClub() {
        // Give
        Faker faker = new Faker();

        User testUser = new User();
        testUser.setUsername(faker.name().username());
        testUser.setFirstName(faker.name().firstName());
        testUser.setLastName(faker.name().lastName());
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword(faker.internet().password());
        testUser.setPhone(faker.phoneNumber().phoneNumber());
        testUser.setTeams(null);

        Role testRole = new Role();
        testRole.setName("TEST_ROLE");
        Set<Role> testRoles = new HashSet<>();
        testRoles.add(testRole);
        testUser.setRoles(testRoles);

        Club testClub = new Club();
        testClub.setName("TestClub");
        testClub.setPresident(testUser);

        //When
        underTest.save(testClub);
        Club savedClub = underTest.findById(testClub.getId()).orElse(null);

        //Then
        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals(testClub.getName(), savedClub.getName());
        Assertions.assertEquals(testClub.getPresident().getId(), savedClub.getPresident().getId());
    }

    @Test  //President von einem Verein wird verändert
    public void save_changePresident_UpdatedRowWithChangedPresident() {
        //Give
        Faker faker = new Faker();

        User testUser = new User();
        testUser.setUsername(faker.name().username());
        testUser.setFirstName(faker.name().firstName());
        testUser.setLastName(faker.name().lastName());
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword(faker.internet().password());
        testUser.setPhone(faker.phoneNumber().phoneNumber());
        testUser.setTeams(null);

        Club testClub = new Club();
        testClub.setName("TestClub");
        testClub.setPresident(testUser);

        Role testRole = new Role();
        testRole.setName("TEST_ROLE");
        Set<Role> testRoles = new HashSet<>();
        testRoles.add(testRole);
        testUser.setRoles(testRoles);

        //When
        underTest.save(testClub);
        Club savedClub = underTest.findById(testClub.getId()).orElse(null);

        //Then
        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals(testClub.getName(), savedClub.getName());

        //When
        User testUser2 = new User();
        testUser2.setUsername(faker.name().username());
        testUser2.setFirstName(faker.name().firstName());
        testUser2.setLastName(faker.name().lastName());
        testUser2.setEmail(faker.internet().emailAddress());
        testUser2.setPassword(faker.internet().password());
        testUser2.setPhone(faker.phoneNumber().phoneNumber());
        testUser2.setTeams(null);

        testClub.setPresident(testUser2); //neuer Präsident wird festgelegt
        underTest.save(testClub); //durch save wird mit JPA direkt die Zeile mit gleichem PK überschrieben
        savedClub = underTest.findAll().get(0);

        //Then
        Assertions.assertEquals(testUser2.getUsername(), savedClub.getPresident().getUsername());
        Assertions.assertEquals(testUser2.getFirstName(), savedClub.getPresident().getFirstName());
        Assertions.assertEquals(testUser2.getLastName(), savedClub.getPresident().getLastName());
        Assertions.assertEquals(testUser2.getEmail(), savedClub.getPresident().getEmail());
        Assertions.assertEquals(testUser2.getPassword(), savedClub.getPresident().getPassword());
        Assertions.assertEquals(testUser2.getPhone(), savedClub.getPresident().getPhone());
        Assertions.assertNull(savedClub.getPresident().getTeams());
    }

    @Test
    public void deleteAll_clubHasConnectedPresident_ShouldDeleteClubsButNotPresidents() {
        // Give
        Faker faker = new Faker();

        User testUser = new User();
        testUser.setUsername(faker.name().username());
        testUser.setFirstName(faker.name().firstName());
        testUser.setLastName(faker.name().lastName());
        testUser.setEmail(faker.internet().emailAddress());
        testUser.setPassword(faker.internet().password());
        testUser.setPhone(faker.phoneNumber().phoneNumber());
        testUser.setTeams(null);

        Club testClub = new Club();
        testClub.setName("TestClub");
        testClub.setPresident(testUser);

        Role testRole = new Role();
        testRole.setName("TEST_ROLE");
        Set<Role> testRoles = new HashSet<>();
        testRoles.add(testRole);
        testUser.setRoles(testRoles);

        User testUser2 = new User();
        testUser2.setUsername(faker.name().username());
        testUser2.setFirstName(faker.name().firstName());
        testUser2.setLastName(faker.name().lastName());
        testUser2.setEmail(faker.internet().emailAddress());
        testUser2.setPassword(faker.internet().password());
        testUser2.setPhone(faker.phoneNumber().phoneNumber());
        testUser2.setTeams(null);

        Club testClub2 = new Club();
        testClub2.setName("TestClub");
        testClub2.setPresident(testUser2);

        Role testRole2 = new Role();
        testRole2.setName("TEST_ROLE");
        Set<Role> testRoles2 = new HashSet<>();
        testRoles2.add(testRole2);
        testUser2.setRoles(testRoles2);

        // When

        userRepository.save(testUser);
        userRepository.save(testUser2);
        underTest.save(testClub);
        underTest.save(testClub2);

        // Then

        List<Club> savedClub = underTest.findAll();
        List<User> savedUsers = userRepository.findAll();

        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals(testUser.getUsername(), savedClub.get(0).getPresident().getUsername());
        Assertions.assertEquals(2, savedClub.size());
        Assertions.assertEquals(2, savedUsers.size());

        // Give

        // When
        underTest.deleteAll();

        List<Club> clubs = underTest.findAll();
        List<User> users = userRepository.findAll();

        // Then

        Assertions.assertEquals(0, clubs.size());
        Assertions.assertEquals(2, users.size());

    }

}


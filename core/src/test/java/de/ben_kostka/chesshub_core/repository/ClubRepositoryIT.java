package de.ben_kostka.chesshub_core.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.chesshub_core.AbstractTestcontainers;
import de.ben_kostka.chesshub_core.model.Club;
import de.ben_kostka.chesshub_core.model.Role;
import de.ben_kostka.chesshub_core.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClubRepositoryIT extends AbstractTestcontainers {

    @Autowired
    private ClubRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        underTest.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldCreateClub() {
        // Give
        Faker faker = new Faker();

        User testUser = User.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().phoneNumber())
                .build();
        userRepository.save(testUser);

        Role testRole = new Role();
        testRole.setName("TEST_ROLE");
        roleRepository.save(testRole);
        testUser.getRoles().add(testRole);
        userRepository.save(testUser);

        Club testClub = Club.builder()
                .name("TestClub")
                .president(testUser)
                .build();

        //When
        underTest.save(testClub);
        Club savedClub = underTest.findById(testClub.getId()).orElse(null);

        //Then
        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals(testClub.getName(), savedClub.getName());
        Assertions.assertEquals(testUser.getId(), savedClub.getPresident().getId());
    }

    @Test
    public void save_changePresident_UpdatedRowWithChangedPresident() {
        //Give
        Faker faker = new Faker();

        User testUser = User.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .build();
        userRepository.save(testUser);

        Club testClub = Club.builder()
                .name("TestClub")
                .president(testUser)
                .build();
        underTest.save(testClub);

        //When
        User testUser2 = User.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .build();
        userRepository.save(testUser2);

        testClub.setPresident(testUser2);
        underTest.save(testClub);
        Club savedClub = underTest.findById(testClub.getId()).get();

        //Then
        Assertions.assertEquals(testUser2.getUsername(), savedClub.getPresident().getUsername());
    }

    @Test
    public void deleteAll_clubHasConnectedPresident_ShouldDeleteClubsButNotPresidents() {
        // Give
        Faker faker = new Faker();

        User testUser1 = User.builder().username(faker.name().username()).email(faker.internet().emailAddress()).build();
        User testUser2 = User.builder().username(faker.name().username()).email(faker.internet().emailAddress()).build();
        userRepository.saveAll(List.of(testUser1, testUser2));

        Club testClub1 = Club.builder().name("Club 1").president(testUser1).build();
        Club testClub2 = Club.builder().name("Club 2").president(testUser2).build();
        underTest.saveAll(List.of(testClub1, testClub2));

        // When
        underTest.deleteAll();

        // Then
        Assertions.assertEquals(0, underTest.count());
        Assertions.assertEquals(2, userRepository.count());
    }
}


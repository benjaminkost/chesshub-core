package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.AbstractTestcontainers;
import de.ben_kostka.benchesster.model.Club;
import de.ben_kostka.benchesster.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClubRepositoryTests extends AbstractTestcontainers {

    @Autowired
    private ClubRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void createClub_withAllAttributes_ShouldCreateClub() {
        //Give
        User testUser = new User();
        testUser.setUsername("test_username");
        testUser.setFirstName("Test_firstname");
        testUser.setLastName("Test_lastname");
        testUser.setEmail("test@mail.de");
        testUser.setPassword("test_password");
        testUser.setPhone("1234");
        testUser.setTeams(null);

        Club testClub = new Club();
        testClub.setName("TestClub");
        testClub.setPresident(testUser);

        //When
        underTest.save(testClub);
        Club savedClub = underTest.findById(1).orElse(null);

        //Then
        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals("TestClub", savedClub.getName());
        Assertions.assertEquals(1, savedClub.getPresident().getUser_ID());
    }

}


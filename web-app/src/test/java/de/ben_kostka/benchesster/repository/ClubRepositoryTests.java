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

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldCreateClub() {
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
        Club savedClub = underTest.findById(1L).orElse(null);
        Club savedClub = underTest.findAll().get(0);

        //Then
        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals("TestClub", savedClub.getName());
        Assertions.assertEquals(1, savedClub.getPresident().getUser_ID());
    }

    @Test  //President von einem Verein wird verändert
    public void save_changePresident_UpdatedRowWithChangedPresident() {
        //Give
        User testUser = new User();
        testUser.setUsername("test_username");
        testUser.setFirstName("test_firstname");
        testUser.setLastName("test_lastname");
        testUser.setEmail("test@mail.de");
        testUser.setPassword("test_password");
        testUser.setPhone("1234");
        testUser.setUserStatus(1);
        testUser.setTeams(null);

        Club testClub = new Club();
        testClub.setName("TestClub");
        testClub.setPresident(testUser);

        //When
        underTest.save(testClub);
        Club savedClub = underTest.findAll().get(0);

        //Then
        Assertions.assertNotNull(savedClub);
        Assertions.assertEquals("TestClub", savedClub.getName());
        Assertions.assertEquals(1, savedClub.getPresident().getId());
        Assertions.assertEquals(1, savedClub.getPresident().getUser_ID());

        //When
        User testUser2 = new User();
        testUser2.setUsername("test_username2");
        testUser2.setFirstName("test_firstname2");
        testUser2.setLastName("test_lastname2");
        testUser2.setEmail("test@mail.de2");
        testUser2.setPassword("test_password2");
        testUser2.setPhone("12345");
        testUser2.setUserStatus(1);
        testUser2.setTeams(null);

        testClub.setPresident(testUser2); //neuer Präsident wird festgelegt
        underTest.save(testClub); //durch save wird mit JPA direkt die Zeile mit gleichem PK überschrieben
        savedClub = underTest.findAll().get(0);

        //Then
        Assertions.assertEquals("test_username2", savedClub.getPresident().getUsername());
        Assertions.assertEquals("test_firstname2", savedClub.getPresident().getFirstName());
        Assertions.assertEquals("test_lastname2", savedClub.getPresident().getLastName());
        Assertions.assertEquals("test@mail.de2", savedClub.getPresident().getEmail());
        Assertions.assertEquals("test_password2", savedClub.getPresident().getPassword());
        Assertions.assertEquals("12345", savedClub.getPresident().getPhone());
        Assertions.assertEquals(1, savedClub.getPresident().getUserStatus());
        Assertions.assertNull(savedClub.getPresident().getTeams());
    }

}


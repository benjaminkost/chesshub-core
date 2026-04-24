package de.ben_kostka.chesshub_core.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.chesshub_core.AbstractTestcontainers;
import de.ben_kostka.chesshub_core.model.Game;
import de.ben_kostka.chesshub_core.model.GameRequest;
import de.ben_kostka.chesshub_core.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameRepositoryIT extends AbstractTestcontainers {
    @Autowired
    private GameRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRequestRepository gameRequestRepository;

    @BeforeEach
    public void setUp() {
        gameRequestRepository.deleteAll();
        underTest.deleteAll();
        userRepository.deleteAll();
    }

    private User createTestUser(Faker faker) {
        User user = User.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
        return userRepository.save(user);
    }

    @Test
    public void save_withAllAttributes_ShouldCreateNewGame() {
        // Give
        Faker faker = new Faker();
        User whiteUser = createTestUser(faker);
        User blackUser = createTestUser(faker);

        Date gameDate = new Date();

        Game game = Game.builder()
                .date(gameDate)
                .round(1)
                .event(faker.book().title())
                .site(faker.book().title())
                .moves("1. e4 c6 2. d4 d5")
                .result("1-0")
                .white_user(whiteUser)
                .black_user(blackUser)
                .white_player_name(whiteUser.getFirstName())
                .black_player_name(blackUser.getFirstName())
                .comment(faker.book().title())
                .build();

        GameRequest request = GameRequest.builder()
                .game(game)
                .sender(whiteUser)
                .recipient(blackUser)
                .build();
        
        game.setRequests(new ArrayList<>(List.of(request)));

        // When
        Game saved = underTest.save(game);
        Game found = underTest.findById(saved.getId()).get();

        // Then
        Assertions.assertNotNull(found);
        Assertions.assertEquals(game.getEvent(), found.getEvent());
        Assertions.assertEquals(whiteUser.getId(), found.getWhite_user().getId());
        Assertions.assertEquals(blackUser.getId(), found.getBlack_user().getId());
        Assertions.assertEquals(1, found.getRequests().size());
    }

    @Test
    public void save_changeWhitePlayer_ShouldUpdateWhitePlayer() {
        // Give
        Faker faker = new Faker();
        User oldWhite = createTestUser(faker);
        User black = createTestUser(faker);
        Game game = Game.builder()
                .white_user(oldWhite)
                .black_user(black)
                .moves("1. e4")
                .build();
        game = underTest.save(game);

        User newWhite = createTestUser(faker);

        // When
        game.setWhite_user(newWhite);
        underTest.save(game);
        Game updated = underTest.findById(game.getId()).get();

        // Then
        Assertions.assertEquals(newWhite.getId(), updated.getWhite_user().getId());
    }

    @Test
    public void deleteAll_gameHasAllAttributes_ShouldDeleteGamesAndRequests() {
        // Give
        Faker faker = new Faker();
        User u1 = createTestUser(faker);
        User u2 = createTestUser(faker);
        Game game = Game.builder().white_user(u1).black_user(u2).moves("1. e4").build();
        GameRequest req = GameRequest.builder().game(game).sender(u1).recipient(u2).build();
        game.setRequests(new ArrayList<>(List.of(req)));
        underTest.save(game);

        // When
        underTest.deleteAll();

        // Then
        Assertions.assertEquals(0, underTest.count());
        Assertions.assertEquals(0, gameRequestRepository.count());
        Assertions.assertEquals(2, userRepository.count());
    }
}

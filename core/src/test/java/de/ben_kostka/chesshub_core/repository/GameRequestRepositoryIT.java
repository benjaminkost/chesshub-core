package de.ben_kostka.chesshub_core.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.chesshub_core.AbstractTestcontainers;
import de.ben_kostka.chesshub_core.enums.GameRequestStatus;
import de.ben_kostka.chesshub_core.model.Game;
import de.ben_kostka.chesshub_core.model.GameRequest;
import de.ben_kostka.chesshub_core.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameRequestRepositoryIT extends AbstractTestcontainers {

    @Autowired
    private GameRequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
        requestRepository.deleteAll();
        gameRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        requestRepository.deleteAll();
        userRepository.deleteAll();
        gameRepository.deleteAll();
    }

    private User createTestUser(Faker faker) {
        User user = User.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
        return userRepository.save(user);
    }

    @Test
    public void save_withAllAttributes_ShouldSaveRequest() {
        // Give
        Faker faker = new Faker();
        User sender = createTestUser(faker);
        User recipient = createTestUser(faker);

        Game game = Game.builder()
                .white_user(sender)
                .black_user(recipient)
                .moves("1. e4")
                .build();
        gameRepository.save(game);

        GameRequest request = GameRequest.builder()
                .game(game)
                .sender(sender)
                .recipient(recipient)
                .build();

        // When
        requestRepository.save(request);
        GameRequest savedRequest = requestRepository.findById(request.getId()).orElse(null);

        // Then
        Assertions.assertNotNull(savedRequest);
        Assertions.assertEquals(sender.getUsername(), savedRequest.getSender().getUsername());
        Assertions.assertEquals(recipient.getUsername(), savedRequest.getRecipient().getUsername());
    }

    @Test
    public void save_changeStatus_ShouldSaveRequest() {
        // Give
        Faker faker = new Faker();
        User sender = createTestUser(faker);
        User recipient = createTestUser(faker);
        Game game = Game.builder().white_user(sender).black_user(recipient).moves("1. e4").build();
        gameRepository.save(game);

        GameRequest request = GameRequest.builder()
                .sender(sender)
                .recipient(recipient)
                .game(game)
                .build();
        requestRepository.save(request);

        // When
        request.setGameRequestStatus(GameRequestStatus.ACCEPTED);
        requestRepository.save(request);
        GameRequest updated = requestRepository.findById(request.getId()).orElse(null);

        // Then
        Assertions.assertNotNull(updated);
        Assertions.assertEquals(GameRequestStatus.ACCEPTED, updated.getGameRequestStatus());
    }

    @Test
    public void deleteRequest_ShouldDeleteRequest() {
        // Give
        Faker faker = new Faker();
        User sender = createTestUser(faker);
        User recipient = createTestUser(faker);
        Game game = Game.builder().white_user(sender).black_user(recipient).moves("1. e4").build();
        gameRepository.save(game);

        GameRequest request = GameRequest.builder()
                .sender(sender)
                .recipient(recipient)
                .game(game)
                .build();
        requestRepository.save(request);

        // When
        requestRepository.delete(request);

        // Then
        Assertions.assertFalse(requestRepository.existsById(request.getId()));
        Assertions.assertEquals(2, userRepository.count());
    }
}

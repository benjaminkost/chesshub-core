package de.ben_kostka.benchesster.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.benchesster.AbstractTestcontainers;
import de.ben_kostka.benchesster.enums.GameRequestStatus;
import de.ben_kostka.benchesster.model.Game;
import de.ben_kostka.benchesster.model.GameRequest;
import de.ben_kostka.benchesster.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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

    }

    @AfterEach
    public void tearDown() {
        requestRepository.deleteAll();
        userRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldSaveRequest() {
        // Give

        Faker faker = new Faker();

        User sender = new User();
        sender.setUsername(faker.name().username());
        sender.setEmail(faker.internet().emailAddress());
        sender.setPassword(faker.internet().password());

        User recipient = new User();
        recipient.setUsername(faker.name().username());
        recipient.setEmail(faker.internet().emailAddress());
        recipient.setPassword(faker.internet().password());

        Game game = new Game();
        game.setBlack_user(sender);
        game.setWhite_user(recipient);
        game.setBlack_player_name(faker.name().fullName());
        game.setWhite_player_name(faker.name().fullName());
        game.setMoves("1. e4 c6 2. d4 d5 3. exd5 cxd5 4. Nc3 Nc6 5. Bb5 Nf6 6. Nge2 Bg4 7. O-O e6 " +
                "8. f3 Bf5 9. a3 a6 10. Ba4 b5 11. Bb3 Be7 12. Ng3 Bg6 13. f4 Qb6 14. Nce2 Nh5 15. c3 O-O 16. f5 Nxg3 " +
                "17. Nxg3 exf5 18. Nxf5 Rad8 19. Nxe7+ Nxe7 20. Bg5 f6 21. Bd2 Rfe8 22. Qg4 f5 23. Qg5 a5 1-0");

        GameRequest request = new GameRequest();
        request.setGame(game);
        request.setSender(sender);
        request.setRecipient(recipient);

        // When

        userRepository.save(sender);
        requestRepository.save(request);
        gameRepository.save(game);
        requestRepository.save(request);

        GameRequest savedRequest = requestRepository.findById(request.getId()).orElse(null);

        // Then

        Assertions.assertNotNull(savedRequest);
        Assertions.assertEquals(sender.getUsername(), savedRequest.getSender().getUsername());
        Assertions.assertEquals(recipient.getUsername(), savedRequest.getRecipient().getUsername());
        Assertions.assertEquals(sender.getEmail(), savedRequest.getSender().getEmail());
    }

    @Test
    public void save_changeStatus_ShouldSaveRequest() {
        // Give
        Faker faker = new Faker();

        GameRequest testRequest = new GameRequest();

        User sender = new User();
        sender.setUsername(faker.name().username());
        sender.setEmail(faker.internet().emailAddress());
        sender.setPassword(faker.internet().password());
        testRequest.setSender(sender);

        User recipient = new User();
        recipient.setUsername(faker.name().username());
        recipient.setEmail(faker.internet().emailAddress());
        recipient.setPassword(faker.internet().password());
        testRequest.setRecipient(recipient);

        Game game = new Game();
        game.setBlack_user(sender);
        game.setWhite_user(recipient);
        game.setBlack_player_name(faker.name().fullName());
        game.setWhite_player_name(faker.name().fullName());
        game.setMoves("1. e4 c6 2. d4 d5 3. exd5 cxd5 4. Nc3 Nc6 5. Bb5 Nf6 6. Nge2 Bg4 7. O-O e6 " +
                "8. f3 Bf5 9. a3 a6 10. Ba4 b5 11. Bb3 Be7 12. Ng3 Bg6 13. f4 Qb6 14. Nce2 Nh5 15. c3 O-O 16. f5 Nxg3 " +
                "17. Nxg3 exf5 18. Nxf5 Rad8 19. Nxe7+ Nxe7 20. Bg5 f6 21. Bd2 Rfe8 22. Qg4 f5 23. Qg5 a5 1-0");
        testRequest.setGame(game);

        // When
        requestRepository.save(testRequest);
        GameRequest savedRequest = requestRepository.findById(testRequest.getId()).orElse(null);

        // Then
        Assertions.assertNotNull(savedRequest);
        Assertions.assertEquals(testRequest.getId(), savedRequest.getId());
        Assertions.assertEquals(sender.getUsername(), savedRequest.getSender().getUsername());
        Assertions.assertEquals(recipient.getUsername(), savedRequest.getRecipient().getUsername());
        Assertions.assertEquals(sender.getEmail(), savedRequest.getSender().getEmail());

        // Give

        // When
        savedRequest.setGameRequestStatus(GameRequestStatus.ACCEPTED);
        requestRepository.save(savedRequest);
        GameRequest changedSavedRequest = requestRepository.findById(testRequest.getId()).orElse(null);

        // Then
        Assertions.assertNotNull(changedSavedRequest);
        Assertions.assertEquals(testRequest.getId(), changedSavedRequest.getId());
        Assertions.assertEquals(sender.getUsername(), changedSavedRequest.getSender().getUsername());
        Assertions.assertEquals(recipient.getUsername(), changedSavedRequest.getRecipient().getUsername());
        Assertions.assertEquals(sender.getEmail(), changedSavedRequest.getSender().getEmail());
        Assertions.assertEquals(savedRequest.getGameRequestStatus(), changedSavedRequest.getGameRequestStatus());
    }

    @Test
    public void delteRequest_ShouldDeleteRequest() {
        // Give
        Faker faker = new Faker();

        GameRequest testRequest = new GameRequest();

        User sender = new User();
        sender.setUsername(faker.name().username());
        sender.setEmail(faker.internet().emailAddress());
        sender.setPassword(faker.internet().password());
        testRequest.setSender(sender);

        User recipient = new User();
        recipient.setUsername(faker.name().username());
        recipient.setEmail(faker.internet().emailAddress());
        recipient.setPassword(faker.internet().password());
        testRequest.setRecipient(recipient);

        Game game = new Game();
        game.setBlack_user(sender);
        game.setWhite_user(recipient);
        game.setBlack_player_name(faker.name().fullName());
        game.setWhite_player_name(faker.name().fullName());
        game.setMoves("1. e4 c6 2. d4 d5 3. exd5 cxd5 4. Nc3 Nc6 5. Bb5 Nf6 6. Nge2 Bg4 7. O-O e6 " +
                "8. f3 Bf5 9. a3 a6 10. Ba4 b5 11. Bb3 Be7 12. Ng3 Bg6 13. f4 Qb6 14. Nce2 Nh5 15. c3 O-O 16. f5 Nxg3 " +
                "17. Nxg3 exf5 18. Nxf5 Rad8 19. Nxe7+ Nxe7 20. Bg5 f6 21. Bd2 Rfe8 22. Qg4 f5 23. Qg5 a5 1-0");
        testRequest.setGame(game);

        // When
        userRepository.save(sender);
        userRepository.save(recipient);
        requestRepository.save(testRequest);
        GameRequest savedRequest = requestRepository.findById(testRequest.getId()).orElse(null);
        List<User> users = userRepository.findAll();

        // Then
        Assertions.assertNotNull(savedRequest);
        Assertions.assertEquals(testRequest.getId(), savedRequest.getId());
        Assertions.assertEquals(sender.getUsername(), savedRequest.getSender().getUsername());
        Assertions.assertEquals(recipient.getUsername(), savedRequest.getRecipient().getUsername());
        Assertions.assertEquals(sender.getEmail(), savedRequest.getSender().getEmail());
        Assertions.assertEquals(2, users.size());

        // Give

        // When
        requestRepository.delete(savedRequest);
        GameRequest changedSavedRequest = requestRepository.findById(savedRequest.getId()).orElse(null);
        List<User> users2 = userRepository.findAll();

        // Then
        Assertions.assertNull(changedSavedRequest);
        Assertions.assertEquals(2, users2.size());
    }
}

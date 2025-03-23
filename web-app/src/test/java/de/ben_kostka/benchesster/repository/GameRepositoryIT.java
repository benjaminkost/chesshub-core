package de.ben_kostka.benchesster.repository;

import com.github.javafaker.Faker;
import de.ben_kostka.benchesster.AbstractTestcontainers;
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

import java.util.ArrayList;
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
        // Create Game
        Faker faker = new Faker();
        Game game = new Game();
        game.setDate(faker.date().birthday());
        game.setRound("1");
        game.setEvent(faker.book().title());
        game.setSite(faker.book().title());
        game.setMoves("1. e4 c6 2. d4 d5 3. exd5 cxd5 4. Nc3 Nc6 5. Bb5 Nf6 6. Nge2 Bg4 7. O-O e6 " +
                "8. f3 Bf5 9. a3 a6 10. Ba4 b5 11. Bb3 Be7 12. Ng3 Bg6 13. f4 Qb6 14. Nce2 Nh5 15. c3 O-O 16. f5 Nxg3 " +
                "17. Nxg3 exf5 18. Nxf5 Rad8 19. Nxe7+ Nxe7 20. Bg5 f6 21. Bd2 Rfe8 22. Qg4 f5 23. Qg5 a5 1-0");
        game.setResult("1-0");

        User whiteUser = new User();
        whiteUser.setUsername(faker.name().username());
        whiteUser.setFirstName(faker.name().firstName());
        whiteUser.setLastName(faker.name().lastName());
        whiteUser.setEmail(faker.internet().emailAddress());
        whiteUser.setPassword(faker.internet().password());
        game.setWhite_user(whiteUser);

        User blackUser = new User();
        blackUser.setUsername(faker.name().username());
        blackUser.setFirstName(faker.name().firstName());
        blackUser.setLastName(faker.name().lastName());
        blackUser.setEmail(faker.internet().emailAddress());
        blackUser.setPassword(faker.internet().password());
        game.setBlack_user(blackUser);

        game.setWhite_player_name(faker.name().firstName());
        game.setBlack_player_name(faker.name().lastName());
        game.setComment(faker.book().title());

        GameRequest gameRequest = new GameRequest();
        gameRequest.setGame(game);
        gameRequest.setSender(whiteUser);
        gameRequest.setRecipient(blackUser);
        game.setRequests(new ArrayList<>(List.of(gameRequest)));

        // Save game
        underTest.save(game);
    }

    @AfterEach
    public void tearDown() {
        underTest.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void save_withAllAttributes_ShouldCreateNewGame() {
        // Give
        Faker faker = new Faker();
        Game game = new Game();
        game.setDate(faker.date().birthday());
        game.setRound("1");
        game.setEvent(faker.book().title());
        game.setSite(faker.book().title());
        game.setMoves("1. e4 c6 2. d4 d5 3. exd5 cxd5 4. Nc3 Nc6 5. Bb5 Nf6 6. Nge2 Bg4 7. O-O e6 " +
                "8. f3 Bf5 9. a3 a6 10. Ba4 b5 11. Bb3 Be7 12. Ng3 Bg6 13. f4 Qb6 14. Nce2 Nh5 15. c3 O-O 16. f5 Nxg3 " +
                "17. Nxg3 exf5 18. Nxf5 Rad8 19. Nxe7+ Nxe7 20. Bg5 f6 21. Bd2 Rfe8 22. Qg4 f5 23. Qg5 a5 1-0");
        game.setResult("1-0");

        User whiteUser = new User();
        whiteUser.setUsername(faker.name().username());
        whiteUser.setFirstName(faker.name().firstName());
        whiteUser.setLastName(faker.name().lastName());
        whiteUser.setEmail(faker.internet().emailAddress());
        whiteUser.setPassword(faker.internet().password());
        game.setWhite_user(whiteUser);

        User blackUser = new User();
        blackUser.setUsername(faker.name().username());
        blackUser.setFirstName(faker.name().firstName());
        blackUser.setLastName(faker.name().lastName());
        blackUser.setEmail(faker.internet().emailAddress());
        blackUser.setPassword(faker.internet().password());
        game.setBlack_user(blackUser);

        game.setWhite_player_name(faker.name().firstName());
        game.setBlack_player_name(faker.name().lastName());
        game.setComment(faker.book().title());

        GameRequest gameRequest = new GameRequest();
        gameRequest.setGame(game);
        gameRequest.setSender(whiteUser);
        gameRequest.setRecipient(blackUser);
        game.setRequests(new ArrayList<>(List.of(gameRequest)));

        // When
        underTest.save(game);
        Game savedGame = underTest.findAll().stream().findFirst().get();

        // Then
        Assertions.assertNotNull(savedGame);
        Assertions.assertEquals(game.getDate(), savedGame.getDate());
        Assertions.assertEquals(game.getRound(), savedGame.getRound());
        Assertions.assertEquals(game.getEvent(), savedGame.getEvent());
        Assertions.assertEquals(game.getSite(), savedGame.getSite());
        Assertions.assertEquals(game.getMoves(), savedGame.getMoves());
        Assertions.assertEquals(game.getWhite_user(), savedGame.getWhite_user());
        Assertions.assertEquals(game.getBlack_user(), savedGame.getBlack_user());
        Assertions.assertEquals(game.getComment(), savedGame.getComment());
        Assertions.assertEquals(game.getWhite_player_name(), savedGame.getWhite_player_name());
        Assertions.assertEquals(game.getBlack_player_name(), savedGame.getBlack_player_name());
        Assertions.assertEquals(game.getRequests().size(), savedGame.getRequests().size());
    }

    @Test
    public void save_changeWhitePlayer_ShouldUpdateWhitePlayer() {
        // Give
        Faker faker = new Faker();
        Game game = underTest.findAll().stream().findFirst().get();

        User newWhiteUser = new User();
        newWhiteUser.setUsername(faker.name().username());
        newWhiteUser.setFirstName(faker.name().firstName());
        newWhiteUser.setLastName(faker.name().lastName());
        newWhiteUser.setEmail(faker.internet().emailAddress());
        newWhiteUser.setPassword(faker.internet().password());

        game.setWhite_user(newWhiteUser);
        game.setWhite_player_name(newWhiteUser.getFirstName()+ " " + newWhiteUser.getLastName());

        // When
        underTest.save(game);
        Game newSavedGame = underTest.findAll().stream().findFirst().get();

        // Then
        Assertions.assertNotNull(newSavedGame);
        Assertions.assertEquals(game.getDate(), newSavedGame.getDate());
        Assertions.assertEquals(game.getRound(), newSavedGame.getRound());
        Assertions.assertEquals(game.getEvent(), newSavedGame.getEvent());
        Assertions.assertEquals(game.getSite(), newSavedGame.getSite());
        Assertions.assertEquals(game.getMoves(), newSavedGame.getMoves());
        Assertions.assertEquals(game.getWhite_user(), newSavedGame.getWhite_user());
        Assertions.assertEquals(game.getBlack_user(), newSavedGame.getBlack_user());
        Assertions.assertEquals(game.getComment(), newSavedGame.getComment());
        Assertions.assertEquals(game.getRequests().size(), newSavedGame.getRequests().size());
    }

    @Test
    public void deleteAll_gameHasAllAttributes_ShouldDeleteGamesAndRequests() {
        // Give
        Game game = underTest.findAll().stream().findFirst().get();

        // When
        underTest.deleteAll();

        List<Game> savedGame = underTest.findAll();
        List<GameRequest> savedRequest = gameRequestRepository.findAll();
        List<User> users = userRepository.findAll();

        // Then
        Assertions.assertEquals(0, savedGame.size());
        Assertions.assertEquals(0, savedRequest.size());
        Assertions.assertEquals(2, users.size());
    }

}

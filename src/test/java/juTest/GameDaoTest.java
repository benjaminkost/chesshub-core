package juTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;

import BusinessObjects.Game;
import BusinessObjects.User;
import DAO.GameDao;
import DAO.UserDao;

/**
 * Test class for GameDao.
 */
public class GameDaoTest {

	private static UserDao userDao;
	private static GameDao gameDao;
	private static User testUser1;
	private static User testUser2;
	private static Game testGame;

	/**
	 * Test case for inserting a game into the database.
	 */
	@Test
	public void testInsertGame() {
		// Create a new game object and set its properties
		testGame = new Game();
		testGame.setEvent("Test Game");
		testGame.setRound("1");
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");

		// Create UserDao and GameDao instances
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();

		// Create test users
		testUser1 = new User();
		testUser1.setFirstname("Black");
		testUser1.setLastname("Doe");
		testUser1.setEmail("johndoe@test.com");
		testUser1.setPassword("test123");
		userDao.insertUser(testUser1);

		testUser2 = new User();
		testUser2.setFirstname("White");
		testUser2.setLastname("Doe");
		testUser2.setEmail("janedoe@test.com");
		testUser2.setPassword("test456");
		userDao.insertUser(testUser2);

		// Set the black and white players for the game
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);

		// Insert the game into the database
		boolean result = gameDao.insertGame(testGame);

		// Assertion to check if the game was inserted successfully
		Assertions.assertTrue(result);

		// Get the inserted game from the database
		int gameId = testGame.getGame_ID();
		Game insertedGame = gameDao.getGameById(gameId);

		// Assertion to check if the retrieved game matches the inserted game
		Assertions.assertEquals(testGame.getGame_ID(), insertedGame.getGame_ID());

		// Clean up: Delete the game and test users from the database
		gameDao.deleteGame(testGame);
		userDao.deleteUser(testUser1);
		userDao.deleteUser(testUser2);
	}

	/**
	 * Test case for retrieving all games from the database.
	 */
	@Test
	public void testGetAllGame() {
		// Get the list of all games from the database
		List<Game> gameList = gameDao.getAllGames();

		// Assertions to check if the game list is not null and not empty
		Assertions.assertNotNull(gameList);
		Assertions.assertFalse(gameList.isEmpty());
	}

	/**
	 * Test case for updating a game in the database.
	 */
	@Test
	public void testUpdateGame() {
		// Create a new game object and set its properties
		testGame = new Game();
		testGame.setEvent("Test Game");
		testGame.setRound("1");
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");
		testGame.setMoves("E6");

		// Create UserDao and GameDao instances
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();

		// Create test users
		testUser1 = new User();
		testUser1.setFirstname("Black");
		testUser1.setLastname("Doe");
		testUser1.setEmail("johndoe@test.com");
		testUser1.setPassword("test123");
		userDao.insertUser(testUser1);

		testUser2 = new User();
		testUser2.setFirstname("White");
		testUser2.setLastname("Doe");
		testUser2.setEmail("janedoe@test.com");
		testUser2.setPassword("test456");
		userDao.insertUser(testUser2);

		// Set the black and white players for the game
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);

		// Insert the game into the database
		gameDao.insertGame(testGame);

		// Get the updated game from the database
		int gameId = testGame.getGame_ID();
		Game updatedGame = gameDao.getGameById(gameId);
		updatedGame.setEvent("Test Game Updated");

		// Update the game in the database
		boolean result = gameDao.updateGame(updatedGame);

		// Assertion to check if the game was updated successfully
		Assertions.assertTrue(result);
		Assertions.assertEquals(testGame.getGame_ID(), updatedGame.getGame_ID());
		Assertions.assertNotEquals(testGame.getEvent(), updatedGame.getEvent());

		// Clean up: Delete the game and test users from the database
		gameDao.deleteGame(testGame);
		userDao.deleteUser(testUser1);
		userDao.deleteUser(testUser2);
	}

	/**
	 * Test case for deleting a game from the database.
	 */
	@Test
	public void testDeleteGame() {
		// Create a new game object and set its properties
		testGame = new Game();
		testGame.setEvent("Test Game");
		testGame.setRound("1");
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");

		// Create UserDao and GameDao instances
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();

		// Create test users
		testUser1 = new User();
		testUser1.setFirstname("Black");
		testUser1.setLastname("Doe");
		testUser1.setEmail("johndoe@test.com");
		testUser1.setPassword("test123");
		userDao.insertUser(testUser1);

		testUser2 = new User();
		testUser2.setFirstname("White");
		testUser2.setLastname("Doe");
		testUser2.setEmail("janedoe@test.com");
		testUser2.setPassword("test456");
		userDao.insertUser(testUser2);

		// Set the black and white players for the game
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);

		// Insert the game into the database
		gameDao.insertGame(testGame);

		// Get the game ID
		int gameId = testGame.getGame_ID();

		// Delete the game from the database
		boolean deleted = gameDao.deleteGame(testGame);

		// Assertion to check if the game was deleted successfully
		Assertions.assertTrue(deleted);

		// Get the deleted game from the database
		Game deletedGame = gameDao.getGameById(gameId);

		// Assertion to check if the game is not found in the database
		Assertions.assertEquals(deletedGame.getGame_ID(), 0);

		// Clean up: Delete the game and test users from the database
		gameDao.deleteGame(testGame);
		userDao.deleteUser(testUser1);
		userDao.deleteUser(testUser2);
	}

	/**
	 * Test case for retrieving a game by its ID from the database.
	 */
	@Test
	public void testGetGameById() {
		// Create a new game object and set its properties
		testGame = new Game();
		testGame.setEvent("Test Game");
		testGame.setRound("1");
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");

		// Create UserDao and GameDao instances
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();

		// Create test users
		testUser1 = new User();
		testUser1.setFirstname("Black");
		testUser1.setLastname("Doe");
		testUser1.setEmail("johndoe@test.com");
		testUser1.setPassword("test123");
		userDao.insertUser(testUser1);

		testUser2 = new User();
		testUser2.setFirstname("White");
		testUser2.setLastname("Doe");
		testUser2.setEmail("janedoe@test.com");
		testUser2.setPassword("test456");
		userDao.insertUser(testUser2);

		// Set the black and white players for the game
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);

		// Insert the game into the database
		gameDao.insertGame(testGame);

		// Get the game ID
		int gameId = testGame.getGame_ID();

		// Retrieve the game from the database using its ID
		Game retrievedGame = gameDao.getGameById(gameId);

		// Assertion to check if the retrieved game matches the inserted game
		Assertions.assertEquals(testGame.getGame_ID(), retrievedGame.getGame_ID());

		// Clean up: Delete the game and test users from the database
		gameDao.deleteGame(testGame);
		userDao.deleteUser(testUser1);
		userDao.deleteUser(testUser2);
	}
}

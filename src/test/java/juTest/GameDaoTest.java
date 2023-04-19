package juTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;


import BusinessObjects.Game;
import BusinessObjects.User;
import dao.GameDao;
import dao.UserDao;

public class GameDaoTest {

	private static UserDao userDao;
	private static GameDao gameDao;
	private static User testUser1;
	private static User testUser2;
	private static Game testGame;


//	@BeforeClass
//	public static void setUp() throws SQLException {
//		userDao = UserDao.getInstance();
//		gameDao = GameDao.getInstance();
//
//		// create test users
//		testUser1 = new User();
//		testUser1.setFirstname("Black");
//		testUser1.setLastname("Doe");
//		testUser1.setEmail("johndoe@test.com");
//		testUser1.setPassword("test123");
//		userDao.insertUser(testUser1);
//
//		testUser2 = new User();
//		testUser2.setFirstname("White");
//		testUser2.setLastname("Doe");
//		testUser2.setEmail("janedoe@test.com");
//		testUser2.setPassword("test456");
//		userDao.insertUser(testUser2);
//
//		// create test game
//		testGame = new Game();
//		testGame.setEvent("Test Game");
//		testGame.setRound(1);
//		testGame.setSite("Test Site");
//		testGame.setDate(Date.valueOf(LocalDate.now()));
//		testGame.setBlack(testUser1);
//		testGame.setWhite(testUser2);
//		testGame.setResult("1/2-1/2");
//		gameDao.insertGame(testGame);
//	}

    @Test
    public void testInsertGame() {
    	testGame = new Game();
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();
		testGame.setEvent("Test Game");
		testGame.setRound(1);
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");
		// create test users
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
		
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);
		
        boolean result = gameDao.insertGame(testGame);
        Assertions.assertTrue(result);
        int gameId = testGame.getGame_ID();
        Game insertedGame = gameDao.getGameById(gameId);
        Assertions.assertEquals(testGame.getGame_ID(), insertedGame.getGame_ID());
    }

	@Test
	public void testGetAllGame() {
		GameDao gameDao = GameDao.getInstance();
		List<Game> gameList = gameDao.getAllGames();
		Assertions.assertNotNull(gameList);
		Assertions.assertFalse(gameList.isEmpty());
	}

	@Test
	public void testUpdateGame() {
		testGame = new Game();
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();
		testGame.setEvent("Test Game");
		testGame.setRound(1);
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");
		testGame.setMoves("E6");
		// create test users
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
		
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);
		gameDao.insertGame(testGame);
		int gameId = testGame.getGame_ID();
        Game updatedGame = gameDao.getGameById(gameId);
		testGame.setEvent("Test Game Updated");
        boolean result = gameDao.updateGame(testGame);
        Assertions.assertTrue(result);
        Assertions.assertEquals(testGame.getGame_ID(), updatedGame.getGame_ID());
        Assertions.assertNotEquals(testGame.getEvent(),updatedGame.getEvent());

	}

	@Test
	public void testDeleteGame() {
		testGame = new Game();
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();
		testGame.setEvent("Test Game");
		testGame.setRound(1);
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");
		// create test users
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
		
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);
		gameDao.insertGame(testGame);
		int gameId = testGame.getGame_ID();
		boolean deleted = gameDao.deleteGame(testGame);
		Assertions.assertTrue(deleted);
		Game deletedGame = gameDao.getGameById(gameId);
		Assertions.assertEquals(deletedGame.getGame_ID(), 0);
	}

	@Test
	public void testGetGameById() {
		testGame = new Game();
		userDao = UserDao.getInstance();
		gameDao = GameDao.getInstance();
		testGame.setEvent("Test Game");
		testGame.setRound(1);
		testGame.setSite("Test Site");
		testGame.setDate(Date.valueOf(LocalDate.now()));
		testGame.setResult("1/2-1/2");
		// create test users
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
		
		testGame.setBlack(testUser1);
		testGame.setWhite(testUser2);
		gameDao.insertGame(testGame);
		int gameId = testGame.getGame_ID();
		
		Game retrievedGame = gameDao.getGameById(gameId);
		Assertions.assertEquals(testGame.getGame_ID(), retrievedGame.getGame_ID());
	}

//	@AfterClass
//	public static void tearDown() throws SQLException {
//		// delete test game
//		gameDao.deleteGame(testGame);
//		
//		// delete test users
//		userDao.deleteUser(testUser1);
//		userDao.deleteUser(testUser2);
//	}

}

package Management;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import BusinessObjects.Game;
import BusinessObjects.Team;
import DAO.GameDao;
import DAO.UserDao;

public class GameManagement {


	/**
	 * This method returns a game as string, identified by its ID
	 *
	 * @param userId - user, which belongs to the game
	 * @param gameId - search parameter for the game
	 * @return game as string, "" if game was not found
	 *
	 * @author Filip Topa
	 */
	public static String gameByGameId(int userId, String gameId) {
		Game partie = GameDao.getInstance().getGameById(Integer.parseInt(gameId));
		for (Game partieVergleich : GameDao.getInstance().getGamesByUserId(userId)) {
			if (partie.getGame_ID() == partieVergleich.getGame_ID()) {
				return partie.getGame();
			}
		}
		return "";
	}

	/**
	 * This method returns a game as string, identified by its ID
	 *
	 * @param teams - list of teams
	 * @param gameId - search parameter for the game
	 *
	 * @return game as string, "" if game was not found
	 *
	 * @author Filip Topa
	 */
	public static String gameByTeamId(List<Team> teams, String gameId) {
		Game partie = GameDao.getInstance().getGameById(Integer.parseInt(gameId));
		for (Team team : teams) {
		for (Game partieVergleich : GameDao.getInstance().getGamesByTeamId(team.getTeam_ID())) {
			if (partie.getGame_ID() == partieVergleich.getGame_ID()) {
				return partie.getGame();
			}
		}
	}return "";}

	/**
	 * This method updates the moves for a Game
	 *
	 * @param game - game which gets moves
	 * @param moves - moves, which need to be added
	 *
	 * @author Filip Topa
	 */
	public static void gameDownload(Game game, String moves) {
		game.setMoves(moves);
		GameDao.getInstance().insertGame(game);
	}

	public static List<Game> gamesByUserId(int userId) {
		return GameDao.getInstance().getGamesByUserId(userId);
	}
	
	public static List<Game> gamesByTeamId(int teamId) {
		return GameDao.getInstance().getGamesByTeamId(teamId);
	}

	public static List<Game> gamesWithoutOpponent(int userId) {
		return GameDao.getInstance().getGamesWithoutOpponent(userId);
	}

	/**
	 * This methods creates a game with metadata, which is given through input
	 *
	 * @param color - color of the player, who creates the Game
	 * @param userId - ID of the player, who creates the Game
	 * @param result - result of game
	 * @param date - date of game
	 * @param round - round, in which the game was played
	 * @param event - event, which belongs to the game
	 * @param site - site, where game was playes
	 * @param opponent - user, who played against the user (userId)
	 *
	 * @return new Game with metadata
	 *
	 * @author Filip Topa
	 */
	public static Game gameToPGN(String color, int userId, String result, String date, String round, String event,
			String site, String opponent) {

		Game game = new Game();

		if (color.equals("White")) {
			game.setWhite(UserDao.getInstance().getUserById(userId));
			game.setBlack(UserDao.getInstance().getUserById((0)));
		} else {
			game.setBlack(UserDao.getInstance().getUserById(userId));
			game.setWhite(UserDao.getInstance().getUserById((0)));
		}

		game.setResult(result);
		try {
			game.setDate(new SimpleDateFormat("yyyy.MM.dd").parse(date));
		} catch (ParseException e) {
		}
		game.setRound(round);
		game.setEvent(event);
		game.setSite(site);
		game.setComment(opponent);

		return game;
	}

	/**
	 * This method creates a PGN through a PGN-file, input color & userID
	 *
	 * @param uploadItems - uploaded files, which should be PGN-files
	 * @param color - selected color by user
	 * @param userId - userId of the user, who uploaded the game
	 *
	 * @return PGN as String
	 *
	 * @author Filip Topa
	 */
	public static String PGN(List<FileItem> uploadItems, String color, int userId) {

		String s = "";

		try {
			// Parse the request
			List<FileItem> items = uploadItems;

			// Process the uploaded items
			for (FileItem item : items) {
				// If the item is a file
				if (!item.isFormField()) {
					// Get the file name
					String fileName = item.getName();

					// Save the file
					File uploadedFile = new File(fileName);
					item.write(uploadedFile);

					// Call the parseFile method of PGNReader class
					PGNReader pgnReader = new PGNReader();

					Game parsedFile = pgnReader.parseFile(uploadedFile);

					// Color
					if (color.equals("White")) {
						parsedFile.setWhite(UserDao.getInstance().getUserById(userId));
						parsedFile.setBlack(UserDao.getInstance().getUserById(0));
						parsedFile.setComment(parsedFile.getCommentBlack());
					} else {
						parsedFile.setBlack(UserDao.getInstance().getUserById(userId));
						parsedFile.setWhite(UserDao.getInstance().getUserById(0));
						parsedFile.setComment(parsedFile.getCommentWhite());
					}

					// safe game with DAO in Database
					GameDao.getInstance().insertGame(parsedFile);
					s = "<a href='./GameByGameIdServlet?gameId=" + parsedFile.getGame_ID() +"'> The file was successfully uploaded and parsed. <br> Click here if you want to see it.</a>";

				}
			}

		} catch (Exception e) {
			if (e.getMessage().isEmpty()){
				s = "No file uploaded.";
			}
			else{
				s = "File must end with .pgn";
			}
		}
		return s;
	}
}

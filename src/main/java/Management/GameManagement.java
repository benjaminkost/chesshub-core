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
	 * 	//TODO @Ben bitte kommentieren
	 * @param userId
	 * @param gameId
	 * @return
	 *
	 * @author Ben Kostka
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
	 * //TODO @Ben bitte kommentieren
	 * @param game
	 * @param moves
	 *
	 * @author Ben Kostka
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
	 * //TODO @Ben bitte kommentieren
	 *
	 * @param color
	 * @param userId
	 * @param result
	 * @param date
	 * @param round
	 * @param event
	 * @param site
	 * @param opponent
	 * @return
	 *
	 * @author Ben Kostka
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
		game.setRound(Integer.parseInt(round));
		game.setEvent(event);
		game.setSite(site);
		game.setComment(opponent);

		return game;
	}

	/**
	 * //TODO @Ben bitte kommentieren
	 * @param uploadItems
	 * @param color
	 * @param userId
	 * @return
	 *
	 * @author Ben Kostka
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
				System.out.print(e.getMessage());
			}
		}
		return s;
	}
	
}

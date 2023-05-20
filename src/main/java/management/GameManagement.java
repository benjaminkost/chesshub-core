package management;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import static management.TeamManagement.isUserPartofTeam;
import businessObjects.Game;
import businessObjects.Team;
import dao.GameDao;
import dao.UserDao;

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
	 * @param teams  - list of teams
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
		}
		return "";
	}

	/**
	 * This method updates the moves for a Game
	 *
	 * @param game  - game which has to be download
	 * @param moves - moves, which need to be added
	 *
	 * @author Filip Topa
	 */
	public static void gameDownload(Game game, String moves) {
		game.setMoves(moves);
		GameDao.getInstance().insertGame(game);
	}

	/**
	 * This methods creates a game with metadata, which is given through input
	 *
	 * @param color    - color of the player, who creates the Game
	 * @param userId   - ID of the player, who creates the Game
	 * @param result   - result of game
	 * @param date     - date of game
	 * @param round    - round, in which the game was played
	 * @param event    - event, which belongs to the game
	 * @param site     - site, where game was playes
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
	 * @param color       - selected color by user
	 * @param userId      - userId of the user, who uploaded the game
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
					s = "<a href='./GameByGameIdServlet?gameId=" + parsedFile.getGame_ID()
							+ "'> The file was successfully uploaded and parsed. <br> Click here if you want to see it.</a>";

				}
			}

		} catch (Exception e) {
			if (e.getMessage().isEmpty()) {
				s = "No file uploaded.";
			} else {
				s = "File must end with .pgn";
			}
		}
		return s;
	}

	public static String[][] getMyGamesJSP(int userId) {
		List<Game> myGames = GameDao.getInstance().getGamesByUserId(userId);
		if (myGames.isEmpty()) {
			String[][] myGamesJSP = { { "You don't have any games!" } };
			return myGamesJSP;
		}
		String[][] myGamesJSP = new String[myGames.size()][7];
		int i = 0;
		for (Game myGame : myGames) {
			myGamesJSP[i][0] = "<tr class=normal onmouseover=this.className='spezial'; onmouseout=this.className='normal'; onclick=window.location.href='./GameByGameIdServlet?gameId="
					+ myGame.getGame_ID() + "';>";
			myGamesJSP[i][1] = myGame.getPlayer(userId);
			myGamesJSP[i][2] = myGame.getOpponent(userId);
			myGamesJSP[i][3] = myGame.getEvent();
			myGamesJSP[i][4] = String.valueOf(myGame.getDate());
			myGamesJSP[i][5] = myGame.getResult();
			myGamesJSP[i][6] = myGame.getMoves().substring(0, (int) Math.round(myGame.getMoves().length() * 0.25))
					+ " ...";
			i++;
		}
		return myGamesJSP;
	}

	public static String[][] getGamesWithoutOpponentJSP(int userId) {
		List<Game> gamesWithoutOpponent = GameDao.getInstance().getGamesWithoutOpponent(userId);
		if (gamesWithoutOpponent.isEmpty()) {
			String[][] gamesWithoutOpponentJSP = { { "There are currently no such games!" } };
			return gamesWithoutOpponentJSP;
		}
		String[][] gamesWithoutOpponentJSP = new String[gamesWithoutOpponent.size()][7];
		int i = 0;
		for (Game gameWithoutOpponent : gamesWithoutOpponent) {
			gamesWithoutOpponentJSP[i][0] = "<tr class=normal onmouseover=this.className='spezial'; onmouseout=this.className='normal'; onclick=window.location.href='./SendRequestServlet?gameId="
					+ gameWithoutOpponent.getGame_ID() + "&recipientId=" + gameWithoutOpponent.getRecipient() + "';>";
			gamesWithoutOpponentJSP[i][1] = gameWithoutOpponent.getOpponent(0);
			gamesWithoutOpponentJSP[i][2] = String.valueOf(gameWithoutOpponent.getDate());
			gamesWithoutOpponentJSP[i][3] = gameWithoutOpponent.getResult();
			gamesWithoutOpponentJSP[i][4] = gameWithoutOpponent.getEvent();
			gamesWithoutOpponentJSP[i][5] = gameWithoutOpponent.getRound();
			gamesWithoutOpponentJSP[i][6] = gameWithoutOpponent.getMoves().substring(0,
					(int) Math.round(gameWithoutOpponent.getMoves().length() * 0.25)) + " ...";
			i++;
		}
		return gamesWithoutOpponentJSP;
	}

	public static String[][] getTeamGamesJSP(int teamId, Team team) {
		List<Game> teamGames = GameDao.getInstance().getGamesByTeamId(teamId);
		;
		if (teamGames.isEmpty()) {
			String[][] teamGamesJSP = { { "This team don't have any games!" } };
			return teamGamesJSP;
		}
		String[][] teamGamesJSP = new String[teamGames.size()][9];
		int i = 0;
		for (Game teamGame : teamGames) {
			teamGamesJSP[i][0] = "<tr class=normal onmouseover=this.className='spezial'; onmouseout=this.className='normal'; onclick=window.location.href='./GameByGameIdServlet?gameId="
					+ teamGame.getGame_ID() + "';>";
			if (isUserPartofTeam(teamGame.getWhite(), team)) {
				teamGamesJSP[i][1] = "<td bgcolor='green'>";
			} else {
				teamGamesJSP[i][1] = "<td>";
			}
			teamGamesJSP[i][2] = teamGame.getWhitePlayer();
			if (isUserPartofTeam(teamGame.getBlack(), team)) {
				teamGamesJSP[i][3] = "<td bgcolor='green'>";
			} else {
				teamGamesJSP[i][3] = "<td>";
			}
			teamGamesJSP[i][4] = teamGame.getBlackPlayer();
			teamGamesJSP[i][5] = teamGame.getEvent();
			teamGamesJSP[i][6] = String.valueOf(teamGame.getDate());
			teamGamesJSP[i][7] = teamGame.getResult();
			teamGamesJSP[i][8] = teamGame.getMoves().substring(0, (int) Math.round(teamGame.getMoves().length() * 0.25))
					+ " ...";
			i++;
		}
		return teamGamesJSP;
	}
}

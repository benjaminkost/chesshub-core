package dao.Interface;

import java.util.List;

import BusinessObjects.Game;

public interface GameDaoIF {

	final String TABLENAME = "game";

	// Collumnnames of Databasetable
	final String COL_GAME_ID = "Game_ID";
	final String COL_EVENT = "Event";
	final String COL_ROUND = "Round";
	final String COL_SITE = "Site";
	final String COL_DATE = "Date";
	final String COL_MOVES = "Moves";
	final String COL_RESULT = "Result";
	final String COL_BLACK = "Black";
	final String COL_WHITE = "White";
	
	//Queries
	final String Q_SELECTALLGAMES = "SELECT * FROM " +TABLENAME;
	final String Q_SELECTBYGAMEID = "SELECT * FROM "+TABLENAME+ " WHERE Game_ID = ? ;";
	final String Q_UPDATEGAME = "UPDATE "+TABLENAME+" SET "+COL_EVENT+"=?, "+COL_ROUND+"=?,"+COL_SITE+"=?,"+COL_DATE+"=?,"+COL_MOVES+"=?, "+COL_RESULT+"=?, "+COL_BLACK+"=?, "+COL_WHITE+"=? WHERE "+ COL_GAME_ID+"=?;";
	final String Q_DELETEGAME = "DELETE FROM "+TABLENAME+" WHERE "+COL_GAME_ID+"=?";
	final String Q_INSERTGAME = "INSERT INTO "+TABLENAME+"("+COL_EVENT+", "+COL_ROUND+", "+COL_SITE+", "+COL_DATE+", "+COL_MOVES+", "+COL_RESULT+", "+COL_BLACK+", "+COL_WHITE+") VALUES (?,?,?,?,?,?,?,?)";

	public List<Game> getAllGames();

	public Game getGameById(int game_id);

	public boolean updateGame(Game game);

	public boolean deleteGame(int game_id);

	public boolean insertGame(Game game);

}

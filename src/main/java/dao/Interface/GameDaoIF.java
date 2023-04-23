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
	final String COL_COMMENT = "Comment";
	
	//Queries
	final String Q_SELECTALLGAMES = "SELECT * FROM " +TABLENAME+";";
	final String Q_SELECTBYGAMEID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_GAME_ID+" = ? ;";
	final String Q_SELECTGAMEBYUSERID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_BLACK+" = ? OR "+COL_WHITE+"=?;";
	final String Q_UPDATEGAME = "UPDATE "+TABLENAME+" SET "+COL_EVENT+"=?, "+COL_ROUND+"=?,"+COL_SITE+"=?,"+COL_DATE+"=?,"+COL_MOVES+"=?, "+COL_RESULT+"=?, "+COL_BLACK+"=?, "+COL_WHITE+"=?, "+COL_COMMENT+"=?  WHERE "+ COL_GAME_ID+"=?;";
	final String Q_DELETEGAME = "DELETE FROM "+TABLENAME+" WHERE "+COL_GAME_ID+"=?";
	final String Q_INSERTGAME = "INSERT INTO "+TABLENAME+"("+COL_EVENT+", "+COL_ROUND+", "+COL_SITE+", "+COL_DATE+", "+COL_MOVES+", "+COL_RESULT+", "+COL_BLACK+", "+COL_WHITE+", "+COL_COMMENT+") VALUES (?,?,?,?,?,?,?,?,?);";

	public List<Game> getAllGames();

	public Game getGameById(int game_id);
	
	public List<Game> getGamesByUserId(int user_id);

	public boolean updateGame(Game game);

	public boolean deleteGame(Game game);

	public boolean insertGame(Game game);

}

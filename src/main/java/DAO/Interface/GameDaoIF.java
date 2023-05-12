package DAO.Interface;

import java.util.List;

import BusinessObjects.Game;

public interface GameDaoIF {

	String TABLENAME = "game";

	// Collumnnames of Databasetable
	String COL_GAME_ID = "Game_ID";
	String COL_EVENT = "Event";
	String COL_ROUND = "Round";
	String COL_SITE = "Site";
	String COL_DATE = "Date";
	String COL_MOVES = "Moves";
	String COL_RESULT = "Result";
	String COL_BLACK = "Black";
	String COL_WHITE = "White";
	String COL_COMMENT = "Comment";
	
	//Queries
	String Q_SELECTALLGAMES = "SELECT * FROM " +TABLENAME+";";
	String Q_SELECTBYGAMEID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_GAME_ID+" = ? ;";
	String Q_SELECTGAMEBYUSERID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_BLACK+" = ? OR "+COL_WHITE+"=? ORDER BY GAME_ID DESC;";
	String Q_SELECTGAMEBYTEAMID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_BLACK+" IN (SELECT user_ID FROM user_has_teams WHERE team_ID = ?) OR "+COL_WHITE+" IN (SELECT user_ID FROM user_has_teams WHERE team_ID = ?) ORDER BY GAME_ID DESC;";
	String Q_SELECTGAMESWITHOUTOPPONENT = "SELECT * FROM "+TABLENAME+ " WHERE ("+COL_BLACK+" = 0 AND "+COL_WHITE+"!=?) OR ("+COL_BLACK+" != ? AND "+COL_WHITE+"=0) ;";
	String Q_UPDATEGAME = "UPDATE "+TABLENAME+" SET "+COL_EVENT+"=?, "+COL_ROUND+"=?,"+COL_SITE+"=?,"+COL_DATE+"=?,"+COL_MOVES+"=?, "+COL_RESULT+"=?, "+COL_BLACK+"=?, "+COL_WHITE+"=?, "+COL_COMMENT+"=?  WHERE "+ COL_GAME_ID+"=?;";
	String Q_DELETEGAME = "DELETE FROM "+TABLENAME+" WHERE "+COL_GAME_ID+"=?";
	String Q_INSERTGAME = "INSERT INTO "+TABLENAME+"("+COL_EVENT+", "+COL_ROUND+", "+COL_SITE+", "+COL_DATE+", "+COL_MOVES+", "+COL_RESULT+", "+COL_BLACK+", "+COL_WHITE+", "+COL_COMMENT+") VALUES (?,?,?,?,?,?,?,?,?);";

	List<Game> getAllGames();

	Game getGameById(int game_id);
	
	List<Game> getGamesByUserId(int user_id);
	
	List<Game> getGamesByTeamId(int team_id);
	
	List<Game> getGamesWithoutOpponent(int user_id);

	boolean updateGame(Game game);

	boolean deleteGame(Game game);

	boolean insertGame(Game game);
}

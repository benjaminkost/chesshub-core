package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Game;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import dao.Interface.GameDaoIF;

public class GameDao implements GameDaoIF, DatabaseConnectorIF {

	private static GameDao instance;


	private GameDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static GameDao getInstance() {
		if (instance == null) {
			instance = new GameDao();
		}
		return instance;

	}

	@Override
	public List<Game> getAllGames() {
		List<Game> gameList = new ArrayList<Game>();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLGAMES);
			while (rs.next()) {
				Game game = new Game();
				game.setGame_ID(rs.getInt(COL_GAME_ID));
				game.setEvent(rs.getString(COL_EVENT));
				game.setRound(rs.getInt(COL_ROUND));
				game.setSite(rs.getString(COL_SITE));
				game.setDate(rs.getDate(COL_DATE));
				game.setResult(rs.getString(COL_RESULT));
				game.setMoves(rs.getString(COL_MOVES));
				game.setBlack(UserDao.getInstance().getUserById(rs.getInt(COL_BLACK)));
				game.setWhite(UserDao.getInstance().getUserById(rs.getInt(COL_WHITE)));
				game.setComment(rs.getString(COL_COMMENT));
				gameList.add(game);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gameList;
	}

	@Override
	public Game getGameById(int game_id) {
		Game game = new Game();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYGAMEID, game_id);
			if (rs.next()) {
				game.setGame_ID(rs.getInt(COL_GAME_ID));
				game.setEvent(rs.getString(COL_EVENT));
				game.setRound(rs.getInt(COL_ROUND));
				game.setSite(rs.getString(COL_SITE));
				game.setDate(rs.getDate(COL_DATE));
				game.setResult(rs.getString(COL_RESULT));
				game.setMoves(rs.getString(COL_MOVES));
				game.setBlack(UserDao.getInstance().getUserById(rs.getInt(COL_BLACK)));
				game.setWhite(UserDao.getInstance().getUserById(rs.getInt(COL_WHITE)));
				game.setComment(rs.getString(COL_COMMENT));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return game;
	}
	
	@Override
	public List<Game> getGamesByUserId(int user_id) {
		List<Game> gameList = new ArrayList<Game>();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTGAMEBYUSERID, user_id, user_id);
			while (rs.next()) {
				Game game = new Game();
				game.setGame_ID(rs.getInt(COL_GAME_ID));
				game.setEvent(rs.getString(COL_EVENT));
				game.setRound(rs.getInt(COL_ROUND));
				game.setSite(rs.getString(COL_SITE));
				game.setDate(rs.getDate(COL_DATE));
				game.setResult(rs.getString(COL_RESULT));
				game.setMoves(rs.getString(COL_MOVES));
				game.setBlack(UserDao.getInstance().getUserById(rs.getInt(COL_BLACK)));
				game.setWhite(UserDao.getInstance().getUserById(rs.getInt(COL_WHITE)));
				game.setComment(rs.getString(COL_COMMENT));
				gameList.add(game);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gameList;
	}		

	@Override
	public boolean updateGame(Game game) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEGAME, game.getEvent(), game.getRound(), game.getSite(),
					game.getDate(), game.getMoves(), game.getResult(), game.getBlack().getUser_Id(),
					game.getWhite().getUser_Id(), game.getGame_ID(), game.getComment()) > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteGame(Game game) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEGAME, game.getGame_ID()) > 0) {
				result = true;
				game = null;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean insertGame(Game game) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance()
					.executeUpdate(Q_INSERTGAME,game.getEvent(), game.getRound(), game.getSite(),
							game.getDate(), game.getMoves(), game.getResult(), game.getBlack().getUser_Id(),
							game.getWhite().getUser_Id(), game.getComment())  > 0) {
				ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
				if (rs.next()) {
					game.setGame_ID(rs.getInt(1));
				}
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}



}

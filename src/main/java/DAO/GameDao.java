package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Game;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import DAO.Interface.GameDaoIF;

/**
 * The GameDao class implements the GameDaoIF interface and the DatabaseConnectorIF interface, providing access to the
 * game data stored in the database. It contains methods to retrieve games by ID, user ID, team ID, and games that don't have an opponent.
 * The class is a singleton, using the getInstance method to obtain the instance of the class.
 */
public class GameDao implements GameDaoIF, DatabaseConnectorIF {

    private static GameDao instance;

    /**
     * Constructs a new GameDao object and connects to the database.
     * This constructor is private to ensure singleton pattern is enforced.
     */
    private GameDao() {
        try {
            DatabaseConnector.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of the GameDao class.
     *
     * @return instance of GameDao
     */
    public static GameDao getInstance() {
        if (instance == null) {
            instance = new GameDao();
        }
        return instance;

    }

    /**
     * Returns a list of all games in the databse
     *
     * @return List of all Games
     */
    @Override
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLGAMES); // Execute SELECT query to retrieve all games
            while (rs.next()) {
                // Create a new Game object, set its properties from the database result and add to List
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
            System.err.println("An error occurred while retrieving all games: " + e.getMessage());
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gameList;
    }

    /**
     * Returns the game with the specified ID from Database.
     *
     * @param game_id
     * @return game with specidied ID
     */
    @Override
    public Game getGameById(int game_id) {
        Game game = new Game();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYGAMEID, game_id); // Execute SELECT query to retrieve a game
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
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return game;
    }

    /**
     * Returns List of Games with specidied Id from Database
     *
     * @param user_id
     * @return List of Game-Objects with specified ID
     */
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
            System.err.println("An error occurred while retrieving the games by UserId (" + user_id + ") : " + e.getMessage());
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gameList;
    }

    /**
     * Returns a List of Games with Players in a specific Team from Database
     *
     * @param team_id
     * @return List of Games-Object with specified Team ID
     */
    @Override
    public List<Game> getGamesByTeamId(int team_id) {
        List<Game> gameList = new ArrayList<Game>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTGAMEBYTEAMID, team_id, team_id);
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
            System.err.println("An error occurred while retrieving the games by TeamId (" + team_id + ") : " + e.getMessage());
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gameList;
    }

    /**
     * Return List of Games with without registred Opponent from Database
     *
     * @param user_id
     * @return List of Games with without registred Opponent
     */

    @Override
    public List<Game> getGamesWithoutOpponent(int user_id) {
        List<Game> gameList = new ArrayList<Game>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTGAMESWITHOUTOPPONENT, user_id, user_id);
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
            System.err.println("An error occurred while retrieving the games by UserId (" + user_id + ") : " + e.getMessage());
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gameList;
    }

    /**
     * Update the specified game in the database
     *
     * @param game
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateGame(Game game) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEGAME, game.getEvent(), game.getRound(), game.getSite(),
                    game.getDate(), game.getMoves(), game.getResult(), game.getBlack().getUser_Id(),
                    game.getWhite().getUser_Id(), game.getComment(), game.getGame_ID()) > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Deletes the specified Game from Database.
     *
     * @param game
     * @return true if the Game was successfully deleted, false otherwise.
     */
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
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Inserts the specified Game object into the database.
     *
     * @param game true if the Game was successfully inserted, false otherwise.
     * @return
     */
    @Override
    public boolean insertGame(Game game) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance()
                    .executeUpdate(Q_INSERTGAME, game.getEvent(), game.getRound(), game.getSite(),
                            game.getDate(), game.getMoves(), game.getResult(), game.getBlack().getUser_Id(),
                            game.getWhite().getUser_Id(), game.getComment()) > 0) {
                ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
                if (rs.next()) {
                    game.setGame_ID(rs.getInt(1));
                }
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

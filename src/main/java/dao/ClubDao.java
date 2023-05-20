package dao;

import java.util.ArrayList;

import java.util.List;

import businessObjects.Club;
import database.DatabaseConnector;
import database.DatabaseConnectorIF;
import dao.daoInterfaces.ClubDaoIF;

import java.sql.*;

/**
 * The ClubDao class provides methods to interact with the database for managing club objects.
 * It implements the ClubDaoIF and DatabaseConnectorIF interfaces.
 */

public class ClubDao implements ClubDaoIF, DatabaseConnectorIF {

    private static ClubDao instance; // singleton instance of the class

    /**
     * Constructs a new ClubDao object and connects to the database.
     * This constructor is private to ensure singleton pattern is enforced.
     */
    private ClubDao() {
        try {
            DatabaseConnector.getInstance().connect(); // Connect to the database when a new instance is created
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the singleton instance of the ClubDao class.
     *
     * @return the ClubDao instance
     */
    public static ClubDao getInstance() {
        if (instance == null) {
            instance = new ClubDao();
        }
        return instance;
    }

    /**
     * Returns a list of all clubs in the database.
     *
     * @return a list of all clubs in the database
     */
    @Override
    public List<Club> getAllClubs() {
        List<Club> clubList = new ArrayList<Club>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLCLUBS);      // Execute SELECT query to retrieve all clubs
            while (rs.next()) {
                // Create a new Club object and set its properties from the database result
                Club club = new Club();
                club.setClub_ID(rs.getInt(COL_CLUB_ID));
                club.setName(rs.getString(COL_NAME));
                club.setPresident(UserDao.getInstance().getUserById(rs.getInt(COL_PRESIDENT)));
                clubList.add(club);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();        // Close the statement
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clubList;
    }

    /**
     * Returns the club with the specified ID.
     *
     * @param clubId the ID of the club to retrieve
     * @return the club with the specified ID
     */
    @Override
    public Club getClubById(int clubId) {
        Club club = new Club();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYCLUBID, clubId); // Execute SELECT query to retrieve club with the specified ID
            if (rs.next()) {
                // Set the properties of the club object from the database result
                club.setClub_ID(rs.getInt(COL_CLUB_ID));
                club.setName(rs.getString(COL_NAME));
                club.setPresident(UserDao.getInstance().getUserById(rs.getInt(COL_PRESIDENT)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement(); // Close the statement
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return club;
    }

    /**
     * Updates the specified club in the database.
     *
     * @param club the club to update
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateClub(Club club) {
        boolean result = false;
        Integer presidentID = null;
        if (club.getPresident() != null && club.getPresident().getUser_Id() != 0) {
            presidentID = club.getPresident().getUser_Id();
        }
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATECLUB, club.getName(), presidentID, club.getClub_ID()) > 0) {     // Execute UPDATE query to update
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
     * Deletes the specified Club object from the database.
     *
     * @param club The Club object to delete from the database.
     * @return true if the Club was successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteClub(Club club) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_DELETECLUB, club.getClub_ID()) > 0) { // Execute UPDATE query to Delete
                result = true;
                club = null;
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
     * Inserts the specified Club object into the database.
     *
     * @param Club The Club object to insert into the database.
     * @return true if the Club was successfully inserted, false otherwise.
     */
    @Override
    public boolean insertClub(Club club) {
        boolean result = false;
        Integer presidentID = null;
        if (club.getPresident() != null && club.getPresident().getUser_Id() != 0) {
            presidentID = club.getPresident().getUser_Id();
        }
        try {
            if (DatabaseConnector.getInstance()
                    .executeUpdate(Q_INSERTCLUB, club.getName(), presidentID) > 0) { // Execute UPDATE query to Insert
                ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
                if (rs.next()) {
                    club.setClub_ID(rs.getInt(1));
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

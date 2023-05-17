package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Team;
import BusinessObjects.User;
import Database.DatabaseConnector;
import DAO.Interface.UserTeamDaoIF;

public class UserTeamDao implements UserTeamDaoIF {

    private static UserTeamDao instance; //Singleton instance of the UserDao class.

    /**
     * Private constructor for the UserTeamDao class.
     * Initializes the database connection when creating a new instance.
     */
    private UserTeamDao() {
        try {
            DatabaseConnector.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Singleton instance of the UserTeamDao class.
     *
     * @return UserTeamDao instance.
     */
    public static UserTeamDao getInstance() {
        if (instance == null) {
            instance = new UserTeamDao();
        }
        return instance;
    }


    /**
     * Method that returns a list of teams associated with a particular user id.
     *
     * @param userId id of the user
     * @return list of teams associated with the user
     */
    @Override
    public List<Team> getTeamsByUserId(int userId) {
        List<Team> TeamList = new ArrayList<Team>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTTEAMSBYUSERID, userId);
            while (rs.next()) {
                Team team = new Team();
                team = TeamDao.getInstance().getTeamById(rs.getInt(COL_TEAM_ID));
                TeamList.add(team);
            }
        } catch (SQLException e) {
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return TeamList;
    }

    /**
     * Method that returns a list of users associated with a particular team.
     *
     * @param team team for which to get all users
     * @return list of users associated with the team
     */
    @Override
    public List<User> getUsersByTeamId(Team team) {
        List<User> UserList = new ArrayList<User>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTUSERSBYTEAMID, team.getTeam_ID());
            while (rs.next()) {
                User user = new User();
                user = UserDao.getInstance().getUserById(rs.getInt(COL_USER_ID));
                UserList.add(user);
            }
        } catch (SQLException e) {
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return UserList;
    }

    /**
     * Method that updates a user's team.
     *
     * @param user     user to update
     * @param fromTeam team to move user from
     * @param toTeam   team to move user to
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateUserInTeam(User user, Team fromTeam, Team toTeam) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEUSERTEAM, toTeam.getTeam_ID(), user.getUser_Id(), fromTeam.getTeam_ID()) > 0) {
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
     * Removes the specified user from the specified team.
     *
     * @param user the user to remove from the team
     * @param team the team to remove the user from
     * @return true if the user was successfully removed from the team, false otherwise
     */
    @Override
    public boolean deleteUserFromTeam(User user, Team team) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEUSERFROMTEAM, user.getUser_Id(), team.getTeam_ID()) > 0) {
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
     * Inserts the specified user into the specified team.
     *
     * @param user the user to insert into the team
     * @param team the team to insert the user into
     * @return true if the user was successfully inserted into the team, false otherwise
     */
    @Override
    public boolean insertUserInTeam(User user, Team team) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_INSERTUSERINTEAM, user.getUser_Id(), team.getTeam_ID()) > 0) {
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

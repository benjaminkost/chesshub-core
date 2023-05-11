package DAO;

import java.util.ArrayList;

import java.util.List;

import BusinessObjects.Team;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import DAO.Interface.TeamDaoIF;

import java.sql.*;

public class TeamDao implements TeamDaoIF, DatabaseConnectorIF {

    private static TeamDao instance; //Singleton instance of the TeamDao class.

    /**
     * Private constructor for the TeamDao class.
     * Initializes the database connection when creating a new instance.
     */
    private TeamDao() {
        try {
            DatabaseConnector.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Singleton instance of the RequestDao class.
     *
     * @return TeamDao instance.
     */
    public static TeamDao getInstance() {
        if (instance == null) {
            instance = new TeamDao();
        }
        return instance;
    }

    /**
     * Retrieves all the teams from the database and returns them as a List.
     *
     * @return A List of all the teams in the database.
     */
    @Override
    public List<Team> getAllTeams() {
        List<Team> TeamList = new ArrayList<Team>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLTEAMS);
            while (rs.next()) {
                Team Team = new Team();
                Team.setTeam_ID(rs.getInt(COL_TEAM_ID));
                Team.setName(rs.getString(COL_NAME));
                Team.setClub(ClubDao.getInstance().getClubById(rs.getInt(COL_CLUB)));
                Team.setLeader(UserDao.getInstance().getUserById(rs.getInt(COL_LEADER)));
                TeamList.add(Team);
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
        return TeamList;
    }

    /**
     * Retrieves a team from the database by its team ID.
     *
     * @param Team_id The ID of the team to retrieve.
     * @return The Team object corresponding to the given team ID.
     */
    @Override
    public Team getTeamById(int Team_id) {
        Team team = new Team();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYTEAMID, Team_id);
            if (rs.next()) {
                team.setTeam_ID(rs.getInt(COL_TEAM_ID));
                team.setName(rs.getString(COL_NAME));
                team.setClub(ClubDao.getInstance().getClubById(rs.getInt(COL_CLUB)));
                team.setLeader(UserDao.getInstance().getUserById(rs.getInt(COL_LEADER)));
            }
            /*
            else {
                team = null;
            }

             */
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnector.getInstance().closeStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return team;
    }


    /**
     * Updates a team in the database.
     *
     * @param Team The Team object to update.
     * @return True if the team was updated successfully, false otherwise.
     */
    @Override
    public boolean updateTeam(Team Team) {
        boolean result = false;
        Integer leaderID = null;
        Integer clubID = null;
        if (Team.getLeader() != null && Team.getLeader().getUser_Id() != 0) {
            leaderID = Team.getLeader().getUser_Id();
        }
        if (Team.getClub() != null && Team.getClub().getClub_ID() != 0) {
            clubID = Team.getClub().getClub_ID();
        }
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATETEAM, Team.getName(), clubID, leaderID, Team.getTeam_ID()) > 0) {
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
     * Delete a team from the database.
     *
     * @param team The Team object to delete.
     * @return True if the team was deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteTeam(Team team) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_DELETETEAM, team.getTeam_ID()) > 0) {
                result = true;
                team = null;
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
     * sert a team in the database.
     *
     * @param Team The Team object to insert.
     * @return True if the team was inserted successfully, false otherwise.
     */
    @Override
    public boolean insertTeam(Team Team) {
        boolean result = false;
        Integer leaderID = null;
        Integer clubID = null;
        if (Team.getLeader() != null) {
            leaderID = Team.getLeader().getUser_Id();
        }
        if (Team.getClub() != null) {
            clubID = Team.getClub().getClub_ID();
        }

        try {
            if (DatabaseConnector.getInstance()
                    .executeUpdate(Q_INSERTTEAM, Team.getName(), clubID, leaderID) > 0) {
                ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
                if (rs.next()) {
                    Team.setTeam_ID(rs.getInt(1));
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

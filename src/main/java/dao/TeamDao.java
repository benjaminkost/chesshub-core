package dao;

import java.util.ArrayList;

import java.util.List;

import BusinessObjects.Team;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import dao.Interface.TeamDaoIF;

import java.sql.*;

public class TeamDao implements TeamDaoIF, DatabaseConnectorIF {

	private static TeamDao instance;

	private TeamDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static TeamDao getInstance() {
		if (instance == null) {
			instance = new TeamDao();
		}
		return instance;
	}

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
			// TODO: handle exception
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return TeamList;
	}

	@Override
	public Team getTeamById(int Team_id){
		Team Team = new Team();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYTEAMID, Team_id);
			if (rs.next()) {
				Team.setTeam_ID(rs.getInt(COL_TEAM_ID));
				Team.setName(rs.getString(COL_NAME));
				Team.setClub(ClubDao.getInstance().getClubById(rs.getInt(COL_CLUB)));
				Team.setLeader(UserDao.getInstance().getUserById(rs.getInt(COL_LEADER)));
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
		return Team;
	}

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
			if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATETEAM, Team.getName(),clubID, leaderID, Team.getTeam_ID()) > 0) {
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

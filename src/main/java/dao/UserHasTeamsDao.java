package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Team;
import BusinessObjects.User;
import Database.DatabaseConnector;
import dao.Interface.UserHasTeamsDaoIF;

public class UserHasTeamsDao implements UserHasTeamsDaoIF{
	
	private static UserHasTeamsDao instance;

	private UserHasTeamsDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static UserHasTeamsDao getInstance() {
		if (instance == null) {
			instance = new UserHasTeamsDao();
		}
		return instance;
	}

	
	@Override
	public List<Team> getTeamsByUserId(User user) {
		List<Team> TeamList = new ArrayList<Team>();
		Team Team = new Team();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTTEAMSBYUSERID, user.getUser_Id());
			while (rs.next()) {
				Team.setTeam_ID(rs.getInt(COL_TEAM_ID));
				TeamList.add(Team);
				Team = null;
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
	public boolean updateUserInTeam(User user, Team fromteam, Team toTeam) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEUSERTEAM, toTeam.getTeam_ID(), user.getUser_Id(), fromteam.getTeam_ID()) > 0) {
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
	public boolean deleteUserFromTeam(User user, Team team) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEUSERFROMTEAM, user.getUser_Id(), team.getTeam_ID()) > 0) {
				result = true;
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
	public boolean insertUserInTeam(User user, Team team) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_INSERTUSERINTEAM, user.getUser_Id(), team.getTeam_ID()) > 0) {
				result = true;
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

}

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
	
	private static UserTeamDao instance;

	private UserTeamDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static UserTeamDao getInstance() {
		if (instance == null) {
			instance = new UserTeamDao();
		}
		return instance;
	}

	
	@Override
	public List<Team> getTeamsByUserId(User user) {
		List<Team> TeamList = new ArrayList<Team>();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTTEAMSBYUSERID, user.getUser_Id());
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
		}
		finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
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
			e.printStackTrace();
		}
		finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}

package DAO;

import java.util.ArrayList;
import java.util.List;

import BusinessObjects.User;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import DAO.Interface.UserDaoIF;

import java.sql.*;

public class UserDao implements UserDaoIF, DatabaseConnectorIF {

	private static UserDao instance;

	private UserDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;

	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLUSERS);
			while (rs.next()) {
				User user = new User();
				user.setUser_Id(rs.getInt(COL_USER_ID));
				user.setFirstname(rs.getString(COL_FIRSTNAME));
				user.setLastname(rs.getString(COL_LASTNAME));
				user.setEmail(rs.getString(COL_EMAIL));
				user.setPassword(rs.getString(COL_PASSWORD));
				userList.add(user);
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
		return userList;
	}

	@Override
	public User getUserById(int User_id) {
		User user = new User();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYUSERID, User_id);
			if (rs.next()) {
				user.setUser_Id(rs.getInt(COL_USER_ID));
				user.setFirstname(rs.getString(COL_FIRSTNAME));
				user.setLastname(rs.getString(COL_LASTNAME));
				user.setEmail(rs.getString(COL_EMAIL));
				user.setPassword(rs.getString(COL_PASSWORD));
			}
			/*
			else{
				user = null;
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
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean result = false;
		Integer authId = null;

		if (user.getAuthorisation() != null && user.getAuthorisation().getAuth_ID() != 0) {
			authId = user.getAuthorisation().getAuth_ID();
		}
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEUSER, user.getFirstname(), user.getLastname(),
					user.getEmail(), user.getPassword(), authId, user.getUser_Id()) > 0) {
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
	public boolean deleteUser(User user) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEUSER, user.getUser_Id()) > 0) {
				result = true;
				user = null;
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
	public boolean insertUser(User user) {
		boolean result = false;
		Integer authId = null;

		if (user.getAuthorisation() != null) {
			authId = user.getAuthorisation().getAuth_ID();
		}
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_INSERTUSER, user.getFirstname(), user.getLastname(),
					user.getEmail(), user.getPassword(), authId) > 0) {
				ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
				if (rs.next()) {
					user.setUser_Id(rs.getInt(1));
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

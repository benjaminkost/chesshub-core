package dao;

import java.util.ArrayList;
import java.util.List;

import BusinessObjects.User;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import dao.Interface.UserDaoIF;

import java.sql.*;

public class UserDao implements UserDaoIF, DatabaseConnectorIF {

	private static UserDao instance;

	private UserDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			User user = new User();
			while (rs.next()) {
				user.setUser_Id(rs.getInt(COL_USER_ID));
				user.setFirstname(rs.getString(COL_FIRSTNAME));
				user.setLastname(rs.getString(COL_LASTNAME));
				user.setEmail(rs.getString(COL_EMAIL));
				user.setPassword(rs.getString(COL_PASSWORD));
				user.setClub(ClubDao.getInstance().getClubById(rs.getInt(COL_CLUB)));
				userList.add(user);
				user = null;
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
		return userList;
	}

	@Override
	public User getUserById(int User_id) {
		User user = new User();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYUSERID, User_id);
			if (rs.next()) {
				user = new User();
				user.setUser_Id(rs.getInt(COL_USER_ID));
				user.setFirstname(rs.getString(COL_FIRSTNAME));
				user.setLastname(rs.getString(COL_LASTNAME));
				user.setEmail(rs.getString(COL_EMAIL));
				user.setPassword(rs.getString(COL_PASSWORD));
				user.setClub(ClubDao.getInstance().getClubById(rs.getInt(COL_CLUB)));
			}
		} catch (SQLException e) {
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
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(
					Q_UPDATEUSER, user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(),user.getClub(), user.getUser_Id() ) > 0) {
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
	public boolean deleteUser(int user_id) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEUSER, user_id) > 0) {
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
	public boolean insertUser(User user) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance()
					.executeUpdate(Q_INSERTUSER,user.getFirstname(), user.getLastname(),user.getEmail(),user.getPassword(),user.getClub() ) > 0) {
				ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
				if (rs.next()) {
					user.setUser_Id(rs.getInt(1));
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

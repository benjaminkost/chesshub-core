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
			ResultSet rs = DatabaseConnector.getInstance().executeQuery("select * from user;");
			while (rs.next()) {
				User user = new User();
				user.setuser_Id(rs.getInt(USER_ID));
				user.setFirstname(rs.getString(FIRSTNAME));
				user.setLastname(rs.getString(LASTNAME));
				user.setEmail(rs.getString(EMAIL));
				user.setPassword(rs.getString(PASSWORD));
				userList.add(user);
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
			ResultSet rs = DatabaseConnector.getInstance().executeQuery("select * from user where User_ID = ? ;", User_id);
			if (rs.next()) {
				user = new User();
				user.setuser_Id(rs.getInt(USER_ID));
				user.setFirstname(rs.getString(FIRSTNAME));
				user.setLastname(rs.getString(LASTNAME));
				user.setEmail(rs.getString(EMAIL));
				user.setPassword(rs.getString(PASSWORD));
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
					"UPDATE user SET Firstname=?, Lastname=?, Email=?, Password=? WHERE User_ID=?;", user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getuser_Id() ) > 0) {
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
			if (DatabaseConnector.getInstance().executeUpdate("DELETE FROM user WHERE user_ID = ?", user_id) > 0) {
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
					.executeUpdate("INSERT INTO user(Firstname, Lastname, Email, Password) VALUES(?, ?, ?,?)",user.getFirstname(), user.getLastname(),user.getEmail(),user.getPassword() ) > 0) {
				ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
				if (rs.next()) {
					user.setuser_Id(rs.getInt(1));
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

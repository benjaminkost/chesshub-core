package dao;

import java.util.ArrayList;
import java.util.List;

import businessObjects.User;
import database.DatabaseConnector;
import database.IDatabaseConnector;
import dao.daoInterfaces.IUserDao;

import java.sql.*;

/**
 * The TeamDao class implements the UserDaoIF interface and provides methods to interact with the database
 * to perform CRUD (Create, Read, Update, Delete) operations on Team objects.
 * This class also implements the DatabaseConnectorIF interface for database connection management.
 */
public class UserDao implements IUserDao, IDatabaseConnector {

    private static UserDao instance; //Singleton instance of the UserDao class.

    /**
     * Private constructor for the UserDao class.
     * Initializes the database connection when creating a new instance.
     */
    private UserDao() {
        try {
            DatabaseConnector.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Singleton instance of the UserDao class.
     *
     * @return UserDao instance.
     */
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;

    }

    /**
     * Retrieves all the users from the database and returns them as a List of User objects.
     *
     * @return List of User objects.
     */
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

    /**
     * Retrieves a user with the specified user_id from the database and returns it as a User object.
     *
     * @param userId User ID of the user to retrieve.
     * @return User object with the specified userId.
     */
    @Override
    public User getUserById(int userId) {
        User user = new User();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYUSERID, userId);
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

    /**
     * Updates the information of an existing user in the database.
     *
     * @param user User object with updated information.
     * @return True if the update was successful, false otherwise.
     */
    @Override
    public boolean updateUser(User user) {
        boolean result = false;

        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEUSER, user.getFirstname(), user.getLastname(),
                    user.getEmail(), user.getPassword(), user.getUser_Id()) > 0) {
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
     * Deletes a user from the database.
     *
     * @param user the user to be deleted
     * @return true if the user was successfully deleted, false otherwise
     */
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

    /**
     * Inserts a new user into the database.
     *
     * @param user the user to be inserted
     * @return true if the user was successfully inserted, false otherwise
     */
    @Override
    public boolean insertUser(User user) {
        boolean result = false;

        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_INSERTUSER, user.getFirstname(), user.getLastname(),
                    user.getEmail(), user.getPassword()) > 0) {
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

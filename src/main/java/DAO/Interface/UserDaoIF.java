package DAO.Interface;

import java.util.List;

import BusinessObjects.User;

public interface UserDaoIF {

	String TABLENAME = "user";
	// Collumnames
	String COL_USER_ID = "User_ID";
	String COL_FIRSTNAME = "Firstname";
	String COL_LASTNAME = "Lastname";
	String COL_EMAIL = "Email";
	String COL_PASSWORD = "Password";

//	 Queries
	String Q_SELECTALLUSERS = "SELECT * FROM " +TABLENAME+";";
	String Q_SELECTBYUSERID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_USER_ID+" = ? ;";
	String Q_UPDATEUSER = "UPDATE "+TABLENAME+" SET "+COL_FIRSTNAME+"=?, "+COL_LASTNAME+"=?, "+COL_EMAIL+"=?, "+COL_PASSWORD+"=? WHERE "+ COL_USER_ID+"=?;";
	String Q_DELETEUSER = "DELETE FROM "+TABLENAME+" WHERE "+COL_USER_ID+"=?;";
	String Q_INSERTUSER = "INSERT INTO "+TABLENAME+"("+COL_FIRSTNAME+","+COL_LASTNAME+","+COL_EMAIL+","+COL_PASSWORD+") VALUES (?,?,?,?);";

	List<User> getAllUser();

	User getUserById(int user_id);

	boolean updateUser(User user);

	boolean deleteUser(User user);

	boolean insertUser(User user);
}

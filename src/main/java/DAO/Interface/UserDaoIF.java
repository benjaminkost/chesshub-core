package DAO.Interface;

import java.util.List;

import BusinessObjects.User;

public interface UserDaoIF {

	final String TABLENAME = "user";
	// Collumnames
	final String COL_USER_ID = "User_ID";
	final String COL_FIRSTNAME = "Firstname";
	final String COL_LASTNAME = "Lastname";
	final String COL_EMAIL = "Email";
	final String COL_PASSWORD = "Password";
	final String COL_LASTLOGIN = "LastLogin";

//	 Queries
	final String Q_SELECTALLUSERS = "SELECT * FROM " +TABLENAME+";";
	final String Q_SELECTBYUSERID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_USER_ID+" = ? ;";
	final String Q_UPDATEUSER = "UPDATE "+TABLENAME+" SET "+COL_FIRSTNAME+"=?, "+COL_LASTNAME+"=?, "+COL_EMAIL+"=?, "+COL_PASSWORD+"=? WHERE "+ COL_USER_ID+"=?;";
	final String Q_DELETEUSER = "DELETE FROM "+TABLENAME+" WHERE "+COL_USER_ID+"=?;";
	final String Q_INSERTUSER = "INSERT INTO "+TABLENAME+"("+COL_FIRSTNAME+","+COL_LASTNAME+","+COL_EMAIL+","+COL_PASSWORD+") VALUES (?,?,?,?);";

	public List<User> getAllUser();

	public User getUserById(int User_id);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	public boolean insertUser(User user);

}

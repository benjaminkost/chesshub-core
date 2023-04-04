package dao.Interface;
import java.util.List;

import BusinessObjects.User;

public interface UserDaoIF{
	
	// Collumnames
	 final String USER_ID = "User_ID";
	 final String FIRSTNAME = "Firstname";
	 final String LASTNAME = "Lastname";
	 final String EMAIL = "Email";
	 final String PASSWORD = "Password";
		
	public List<User> getAllUser ();
	public User getUserById (int User_id);
	public boolean updateUser (User user);
	public boolean deleteUser (int user_id);
	public boolean insertUser (User user);
	

}

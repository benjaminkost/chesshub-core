package Management;

import BusinessObjects.User;
import DAO.UserDao;

public class UserManagement {
	/**
	 * This method checks, if the given credentials are correct
	 *
	 * @param userEmail         - provided email by user's input
	 * @param decryptedPassword - provided password by user's input
	 * @return userId, if login succeed; -1 if user doesn't exist, -2 if password is wrong
	 *
	 * @author Lukas Zander
	 */

	public static int loginUser(String userEmail, String decryptedPassword) {
		boolean userExists = false;
		String encryptedPasswordInput = StringEncrypter.encryptString(decryptedPassword);
		int userID = -1;

		// Getting User by Email, Check if User exists
		for (User u : UserDao.getInstance().getAllUser()) {
			if (u.getEmail().equals(userEmail)) {
				userExists = true;
				userID = u.getUser_Id();
			}
		}

		// Password Check
		if (userExists) {
			if (encryptedPasswordInput.equals(UserDao.getInstance().getUserById(userID).getPassword()))
				return userID;
			else
				return -2;
		} else
			return -1;
	}

	/**
	 * This method saves a new User
	 *
	 * @param newUser - user data provided by input
	 * @return true if User was saved
	 *
	 * @author Lukas Zander
	 */
	public static boolean saveNewUser(User newUser) {
		// Check, if Email is already used
		for (User u : UserDao.getInstance().getAllUser()) {
			if (u.getEmail().equals(newUser.getEmail())) {
				System.out.println("Error: Email already used");
				return false;
			}
		}
		// Based on the idea, the input is still decrypted; BUT by calling the method in
		// the servlet, the password gets encrypted!!!
		UserDao.getInstance().insertUser(newUser);
		return true;
	}

	/**
	 * This method returns a user, who is identified by given ID
	 *
	 * @param userId - search parameter for user
	 * @return matching user
	 *
	 * @author Lukas Zander
	 */
	public static User getUserById(int userId) {
		return UserDao.getInstance().getUserById(userId);
	}


	/**
	 * This method returns a user, who is identified by given Mail
	 *
	 * @param mail - search parameter for user
	 * @return matching user if found, else null
	 *
	 * @author Lukas Zander
	 */
	public static User getUserByMail(String mail){
		for (User u: UserDao.getInstance().getAllUser()){
			if (u.getEmail().equals(mail)){
				return u;
			}
		}
		return null;
	}
}

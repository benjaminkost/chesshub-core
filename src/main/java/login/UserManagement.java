package login;

import BusinessObjects.User;
import dao.UserDao;

public class UserManagement {
    /**
     * This method checks, if the given credentials are correct
     *
     * @param userEmail -  provided email by user's input
     * @param decryptedPassword - provided password by user's input
     * @return boolean, if login was successfully
     *
     * @author ZanderLK
     */
    public static boolean loginUser(String userEmail, String decryptedPassword){
        boolean userExists = false;
        String encryptedPasswordInput = StringEncrypter.encryptString(decryptedPassword);
        int userID = -1;

        //Getting User by Email, Check if User exists
        for (User u : UserDao.getInstance().getAllUser()){
            if (u.getEmail().equals(userEmail)){
                userExists = true;
                userID = u.getUser_Id();
            }
        }

        //Password Check
        if (userExists){
            if(encryptedPasswordInput.equals(UserDao.getInstance().getUserById(userID).getPassword())){
                System.out.println("Login successfully!");
                return true;
            }
            else {
                System.out.println("Error: Wrong password!");
                return false;
            }
        }
        else {
            System.out.println("Error: User doesn't exist!");
            return false;
        }
    }

    /**
     * This method saves a new User
     * @param newUser - user data provided by input
     * @return if User was saved
     *
     * @author ZanderLK
     */
    public static boolean saveNewUser(User newUser){
        //Check, if Email is already used
        for (User u : UserDao.getInstance().getAllUser()){
            if (u.getEmail().equals(newUser.getEmail())){
                System.out.println("Error: Email already used");
                return false;
            }
        }
        // Based on the idea, the input is still decrypted; BUT by calling the method in the servlet, the password gets encrypted!!!
        UserDao.getInstance().insertUser(newUser);
        return true;
    }

}

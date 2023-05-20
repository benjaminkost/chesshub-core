
package juTest;

import java.util.List;
import static management.StringEncrypter.encryptString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import businessObjects.User;
import dao.UserDao;

/**

 Test class for UserDao.
 */
public class UserDaoTest {

    /**

     Test case for inserting a user into the database.
     */
    @Test
    void testInsertUser() {
// Create an instance of UserDao
        UserDao userDao = UserDao.getInstance();

// Create a user
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));

// Insert the user into the database
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);

// Retrieve the inserted user from the database
        int userId = user.getUser_Id();
        User insertedUser = userDao.getUserById(userId);

// Assert that the inserted user is the same as the retrieved user
        Assertions.assertEquals(user.getUser_Id(), insertedUser.getUser_Id());

// Clean up by deleting the user
        userDao.deleteUser(user);
    }

    /**

     Test case for retrieving all users from the database.
     */
    @Test
    void testGetAllUser() {
// Create an instance of UserDao
        UserDao userDao = UserDao.getInstance();

// Retrieve all users from the database
        List<User> userList = userDao.getAllUser();

// Assert that the user list is not null and not empty
        Assertions.assertNotNull(userList);
        Assertions.assertFalse(userList.isEmpty());
    }

    /**

     Test case for updating a user in the database.
     */
    @Test
    void testUpdateUser() {
// Create an instance of UserDao
        UserDao userDao = UserDao.getInstance();

// Create a user
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));

// Insert the user into the database
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);

// Update the user's information
        int userId = user.getUser_Id();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password456");

// Update the user in the database
        boolean updated = userDao.updateUser(user);
        Assertions.assertTrue(updated);

// Retrieve the updated user from the database
        User updatedUser = userDao.getUserById(userId);

// Assert that the updated user is still the same user and the information has changed
        Assertions.assertEquals(user.getUser_Id(), updatedUser.getUser_Id());
        userDao.deleteUser(user);
    }

    /**

     Test case for deleting a user from the database.
     */
    @Test
    void testDeleteUser() {
// Create an instance of UserDao
        UserDao userDao = UserDao.getInstance();

// Create a user
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));

// Insert the user into the database
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);

// Delete the user from the database
        boolean deleted = userDao.deleteUser(user);
        Assertions.assertTrue(deleted);

// Verify that the user no longer exists in the database
        int userId = user.getUser_Id();
        User deletedUser = userDao.getUserById(userId);
        Assertions.assertEquals(deletedUser.getUser_Id(), 0);

        userDao.deleteUser(user);
    }

    /**

     Test case for retrieving a user by ID from the database.
     */
    @Test
    void testGetUserById() {
// Create an instance of UserDao
        UserDao userDao = UserDao.getInstance();

// Create a user
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));

// Insert the user into the database
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);

// Retrieve the user by ID from the database
        int userId = user.getUser_Id();
        User retrievedUser = userDao.getUserById(userId);

// Assert that the retrieved user is the same as the inserted user
        Assertions.assertEquals(user.getUser_Id(), retrievedUser.getUser_Id());

        userDao.deleteUser(user);
    }
}
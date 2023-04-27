package juTest;

import java.util.List;
import static Management.StringEncrypter.encryptString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import BusinessObjects.User;
import dao.UserDao;

public class UserDaoTest {

    @Test
    void testInsertUser() {
        UserDao userDao = UserDao.getInstance();
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);
        int userId = user.getUser_Id();
        User insertedUser = userDao.getUserById(userId);
        Assertions.assertEquals(user.getUser_Id(), insertedUser.getUser_Id());
    }

    @Test
    void testGetAllUser() {
        UserDao userDao = UserDao.getInstance();
        List<User> userList = userDao.getAllUser();
        Assertions.assertNotNull(userList);
        Assertions.assertFalse(userList.isEmpty());
    }

    @Test
    void testUpdateUser() {
        UserDao userDao = UserDao.getInstance();
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);
        int userId = user.getUser_Id();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password456");
        boolean updated = userDao.updateUser(user);
        Assertions.assertTrue(updated);
        User updatedUser = userDao.getUserById(userId);
        Assertions.assertEquals(user.getUser_Id(), updatedUser.getUser_Id());

    }

    @Test
    void testDeleteUser() {
        UserDao userDao = UserDao.getInstance();
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);
        int userId = user.getUser_Id();
        boolean deleted = userDao.deleteUser(user);
        Assertions.assertTrue(deleted);
        User deletedUser = userDao.getUserById(userId);
        Assertions.assertEquals(deletedUser.getUser_Id(), 0);
    }

    @Test
    void testGetUserById() {
        UserDao userDao = UserDao.getInstance();
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword(encryptString("password123"));
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);
        int userId = user.getUser_Id();
        User retrievedUser = userDao.getUserById(userId);
        Assertions.assertEquals(user.getUser_Id(), retrievedUser.getUser_Id());
    }
}

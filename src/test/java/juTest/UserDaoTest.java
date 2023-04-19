package juTest;

import org.junit.jupiter.api.*;

import java.util.List;

import BusinessObjects.User;
import BusinessObjects.Club;
import dao.ClubDao;
import dao.UserDao;

public class UserDaoTest {

    @Test
    void testInsertUser() {
        UserDao userDao = UserDao.getInstance();
        User user = new User();
        user.setFirstname("Max");
        user.setLastname("Mustermann");
        user.setEmail("max.mustermann@example.com");
        user.setPassword("password123");
        Club club = new Club();
        club.setName("TestInsertUser");
        ClubDao.getInstance().insertClub(club);
        user.setClub(club);
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
        user.setPassword("password123");
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
        user.setPassword("password123");
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
        user.setPassword("password123");
        boolean result = userDao.insertUser(user);
        Assertions.assertTrue(result);
        int userId = user.getUser_Id();
        User retrievedUser = userDao.getUserById(userId);
        Assertions.assertEquals(user.getUser_Id(), retrievedUser.getUser_Id());
    }
}

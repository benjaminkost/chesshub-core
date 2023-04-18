package juTest;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import BusinessObjects.User;
import dao.UserDao;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserDaoTest {

	private UserDao userDao;

	@Before
	public void setUp() throws Exception {
		userDao = UserDao.getInstance();
	}
	
	@Test
    @Order(1)
	public void testInsertUser() {
		User user = new User();
		user.setFirstname("Max");
		user.setLastname("Mustermann");
		user.setEmail("max.mustermann@test.com");
		user.setPassword("password");
		assertTrue(userDao.insertUser(user));
		assertNotNull(user.getUser_Id());
	}
	@Test
    @Order(2)
	public void testGetAllUser() {
		List<User> userList = userDao.getAllUser();
		assertNotNull(userList);
		assertTrue(userList.size() > 0);
		assertEquals("Max", userList.get(0).getFirstname());
		assertEquals("Mustermann", userList.get(0).getLastname());
		assertEquals("max.mustermann@test.com", userList.get(0).getEmail());
	}
	
	@Test
    @Order(3)
	public void testGetUserById() {
		User user = userDao.getUserById(2);
		assertNotNull(user);
	}
	@Test
    @Order(4)
	public void testUpdateUser() {
		User user = userDao.getUserById(2);
		user.setFirstname("Maximilian");
		assertTrue(userDao.updateUser(user));
		User updatedUser = userDao.getUserById(2);
		assertEquals("Maximilian", updatedUser.getFirstname());
	}
	@Test
    @AfterAll
	public void testzDeleteUser() {
		assertTrue(userDao.deleteUser(1));
		assertFalse(userDao.deleteUser(1));
		assertNull(userDao.getUserById(1));
	}
}


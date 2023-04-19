package juTest;

import BusinessObjects.Authorisation;
import dao.AuthDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AuthDaoTest {

    @Test
    void testInsertAuth() {
        AuthDao authdao = AuthDao.getInstance();
        Authorisation auth = new Authorisation();
        auth.setRole("InsertTestAuth");
        boolean result = authdao.insertAuth(auth);
        Assertions.assertTrue(result);
        int authId = auth.getAuth_ID();
        Authorisation insertedAuth = authdao.getAuthById(authId);
        Assertions.assertEquals(auth.getAuth_ID(), insertedAuth.getAuth_ID());
    }

    @Test
    void testGetAllAuths() {
        AuthDao authdao = AuthDao.getInstance();
        List<Authorisation> authList = authdao.getAllAuths();
        Assertions.assertNotNull(authList);
        Assertions.assertFalse(authList.isEmpty());
    }

    @Test
    void testUpdateAuth() {
        AuthDao authdao = AuthDao.getInstance();
        Authorisation auth = new Authorisation();
        auth.setRole("UpdateTestAuth");
        boolean result = authdao.insertAuth(auth);
        Assertions.assertTrue(result);
        int authId = auth.getAuth_ID();
        Authorisation updatedAuth = authdao.getAuthById(authId);
        updatedAuth.setRole("TestUpdated Auth");
        boolean updated = authdao.updateAuth(updatedAuth);
        Assertions.assertTrue(updated);
        Assertions.assertEquals(auth.getAuth_ID(), updatedAuth.getAuth_ID());
        Assertions.assertNotEquals(auth.getRole(), updatedAuth.getAuth_ID());
    }

    @Test
    void testDeleteAuth() {
        AuthDao authdao = AuthDao.getInstance();
        Authorisation auth = new Authorisation();
        auth.setRole("TestAuth");
        boolean result = authdao.insertAuth(auth);
        Assertions.assertTrue(result);
        int authId = auth.getAuth_ID();
        boolean deleted = authdao.deleteAuth(auth);
        Assertions.assertTrue(deleted);
        Authorisation deletedAuth = authdao.getAuthById(authId);
        Assertions.assertEquals(deletedAuth.getAuth_ID(), 0);
    }

    @Test
    void testGetAuthById() {
        AuthDao authdao = AuthDao.getInstance();
        Authorisation auth = new Authorisation();
        auth.setRole("GetAuthByIdTest");
        boolean result = authdao.insertAuth(auth);
        Assertions.assertTrue(result);
        int authId = auth.getAuth_ID();
        Authorisation retrievedAuth = authdao.getAuthById(authId);
        Assertions.assertEquals(auth.getAuth_ID(), retrievedAuth.getAuth_ID());
    }
}

package juTest;

import BusinessObjects.Club;
import BusinessObjects.User;
import DAO.ClubDao;
import DAO.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
/**
 * Test class for ClubDao.
 */
public class ClubDaoTest {

    /**
     * Tests the insertion of a club into the database.
     */
    @Test
    void testInsertClub() {
        // Initialization
        ClubDao clubdao = ClubDao.getInstance();
        Club club = new Club();
        club.setName("InsertTest Club");
        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("Chester");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        UserDao.getInstance().insertUser(president);
        club.setPresident(president);
        // Inserting the club
        boolean result = clubdao.insertClub(club);

        // Check the result
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();
        Club insertedClub = clubdao.getClubById(clubId);
        Assertions.assertEquals(club.getClub_ID(), insertedClub.getClub_ID());

        // Clean up test data
        clubdao.deleteClub(club);
        UserDao.getInstance().deleteUser(president);
    }

    @Test
    void testGetAllClubs() {
        ClubDao clubDao = ClubDao.getInstance();
        List<Club> clubList = clubDao.getAllClubs();
        Assertions.assertNotNull(clubList);
        Assertions.assertFalse(clubList.isEmpty());
    }

    @Test
    void testUpdateClub() {
        // Initialization
        ClubDao clubdao = ClubDao.getInstance();

        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("Chester");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        UserDao.getInstance().insertUser(president);

        Club club = new Club();
        club.setName("UpdatedTest Club");
        club.setPresident(president);

        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);

        // Update the club
        int clubId = club.getClub_ID();
        Club updatedClub = clubdao.getClubById(clubId);
        updatedClub.setName("TestUpdated Club");
        boolean updated = clubdao.updateClub(updatedClub);

        // Check the result
        Assertions.assertTrue(updated);
        Assertions.assertEquals(club.getClub_ID(), updatedClub.getClub_ID());
        Assertions.assertNotEquals(club.getName(), updatedClub.getName());

        // Clean up test data
        clubdao.deleteClub(club);
        UserDao.getInstance().deleteUser(president);
    }

    @Test
    void testDeleteClub() {
        // Initialization
        ClubDao clubdao = ClubDao.getInstance();

        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("Chester");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        UserDao.getInstance().insertUser(president);

        Club club = new Club();
        club.setName("DeleteTest Club");
        club.setPresident(president);
        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();

        boolean deleted = clubdao.deleteClub(club);         // Delete the club
        // Check the result
        Assertions.assertTrue(deleted);
        Club deletedClub = clubdao.getClubById(clubId);
        Assertions.assertEquals(deletedClub.getClub_ID(), 0);

        // Clean up test data
        UserDao.getInstance().deleteUser(president);
    }

    @Test
    void testGetClubById() {
        // Initialization
        ClubDao clubdao = ClubDao.getInstance();

        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("Chester");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        UserDao.getInstance().insertUser(president);

        Club club = new Club();
        club.setName("GetClubByIdTest Club");
        club.setPresident(president);
        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();
        // Check the result
        Club retrievedTeam = clubdao.getClubById(clubId);
        Assertions.assertEquals(club.getClub_ID(), retrievedTeam.getClub_ID());

        // Clean up test data
        clubdao.deleteClub(club);
        UserDao.getInstance().deleteUser(president);
    }

}

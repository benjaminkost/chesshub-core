package juTest;

import BusinessObjects.Club;
import BusinessObjects.User;
import dao.ClubDao;
import dao.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClubDaoTest {

    @Test
    void testInsertClub() {
        ClubDao clubdao = ClubDao.getInstance();

        Club club = new Club();
        club.setName("InsertTest Club");
        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("Chester");
        UserDao.getInstance().insertUser(president);
        club.setPresident(president);
        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();
        Club insertedClub = clubdao.getClubById(clubId);
        Assertions.assertEquals(club.getClub_ID(), insertedClub.getClub_ID());
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
        ClubDao clubdao = ClubDao.getInstance();
        Club club = new Club();
        club.setName("UpdatedTest Club");
        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();
        Club updatedClub = clubdao.getClubById(clubId);
        updatedClub.setName("TestUpdated Club");
        boolean updated = clubdao.updateClub(updatedClub);
        Assertions.assertTrue(updated);
        Assertions.assertEquals(club.getClub_ID(), updatedClub.getClub_ID());
        Assertions.assertNotEquals(club.getName(), updatedClub.getName());
    }

    @Test
    void testDeleteClub() {
        ClubDao clubdao = ClubDao.getInstance();
        Club club = new Club();
        club.setName("DeleteTest Club");
        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();
        boolean deleted = clubdao.deleteClub(club);
        Assertions.assertTrue(deleted);
        Club deletedClub = clubdao.getClubById(clubId);
        Assertions.assertEquals(deletedClub.getClub_ID(), 0);
    }

    @Test
    void testGetClubById() {
        ClubDao clubdao = ClubDao.getInstance();
        Club club = new Club();
        club.setName("GetClubByIdTest Club");
        boolean result = clubdao.insertClub(club);
        Assertions.assertTrue(result);
        int clubId = club.getClub_ID();
        Club retrievedTeam = clubdao.getClubById(clubId);
        Assertions.assertEquals(club.getClub_ID(), retrievedTeam.getClub_ID());
    }

}

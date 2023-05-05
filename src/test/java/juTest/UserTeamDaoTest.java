package juTest;

import BusinessObjects.Team;
import BusinessObjects.User;
import DAO.TeamDao;
import DAO.UserDao;
import DAO.UserTeamDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTeamDaoTest {

    @Test
    void testInsertUserInTeam() {
        TeamDao teamDao = TeamDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        UserTeamDao uhtDao= UserTeamDao.getInstance();
        Team team = new Team();
        team.setName("InsertUserInTeamTest");
        User user = new User();
        user.setLastname("InsertUserInTeamTest");
        user.setFirstname("InsertUserInTeamTest");
        user.setEmail("janedoe@test.com");
        user.setPassword("test456");
        user.addTeam(team);
        //team.addMember(user);
        teamDao.insertTeam(team);
        userDao.insertUser(user);
        boolean result = uhtDao.insertUserInTeam(user, team);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetTeamByUserId() {
        TeamDao teamDao = TeamDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        UserTeamDao uhtDao= UserTeamDao.getInstance();
        Team team = new Team();
        team.setName("InsertUserInTeamTest");
        User user = new User();
        user.setLastname("InsertUserInTeamTest");
        user.setFirstname("InsertUserInTeamTest");
        user.setEmail("janedoe@test.com");
        user.setPassword("test456");
        //team.addMember(user);
        teamDao.insertTeam(team);
        userDao.insertUser(user);
        boolean result = uhtDao.insertUserInTeam(user, team);
        Assertions.assertTrue(result); // Prüfung ob Eintrag in die Datenbank gespeichert
        user.setTeams(uhtDao.getTeamsByUserId(user.getUser_Id()));
        Assertions.assertFalse(user.getTeams().isEmpty()); // Prüfung ob Liste Leer
        Assertions.assertEquals(user.getTeams().get(0).getTeam_ID(), team.getTeam_ID()); // Prüfung ob Objekt in Liste gleich Teamobjekt
    }

//    @Test
//    void testUpdateTeam() {
//        TeamDao teamDao = TeamDao.getInstance();
//        Team team = new Team();
//        team.setName("TeamTest");
//        boolean result = teamDao.insertTeam(team);
//        Assertions.assertTrue(result);
//        int teamId = team.getTeam_ID();
//        Team updatedTeam = teamDao.getTeamById(teamId);
//        updatedTeam.setName("TeamTestUpdate");
//        boolean updated = teamDao.updateTeam(updatedTeam);
//        Assertions.assertTrue(updated);
//        Assertions.assertEquals(team.getTeam_ID(), updatedTeam.getTeam_ID());
//        Assertions.assertNotEquals(team.getName(),updatedTeam.getName());
//    }
//
//    @Test
//    void testDeleteTeam() {
//        TeamDao teamDao = TeamDao.getInstance();
//        Team team = new Team();
//        team.setName("TeamTest");
//        boolean result = teamDao.insertTeam(team);
//        Assertions.assertTrue(result);
//        int teamId = team.getTeam_ID();
//        boolean deleted = teamDao.deleteTeam(team);
//        Assertions.assertTrue(deleted);
//        Team deletedTeam = teamDao.getTeamById(teamId);
//        Assertions.assertEquals(deletedTeam.getTeam_ID(), 0);
//    }
//
//    @Test
//    void testGetTeamById() {
//        TeamDao teamDao = TeamDao.getInstance();
//        Team team = new Team();
//        team.setName("TeamTest");
//        boolean result = teamDao.insertTeam(team);
//        Assertions.assertTrue(result);
//        int teamId = team.getTeam_ID();
//        Team retrievedTeam = teamDao.getTeamById(teamId);
//        Assertions.assertEquals(team.getTeam_ID(), retrievedTeam.getTeam_ID());
//    }

}

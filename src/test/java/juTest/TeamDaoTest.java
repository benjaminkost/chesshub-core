package juTest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import BusinessObjects.Team;
import DAO.TeamDao;

public class TeamDaoTest {
	
	@Test
    void testInsertTeam() {
        TeamDao teamDao = TeamDao.getInstance();
        Team team = new Team();
        team.setName("TeamTest");
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);
        int teamId = team.getTeam_ID();
        Team insertedTeam = teamDao.getTeamById(teamId);
        Assertions.assertEquals(team.getTeam_ID(), insertedTeam.getTeam_ID());
    }

    @Test
    void testGetAllTeams() {
        TeamDao teamDao = TeamDao.getInstance();
        List<Team> teamList = teamDao.getAllTeams();
        Assertions.assertNotNull(teamList);
        Assertions.assertFalse(teamList.isEmpty());
    }

    @Test
    void testUpdateTeam() {
    	TeamDao teamDao = TeamDao.getInstance();
        Team team = new Team();
        team.setName("TeamTest");
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);
        int teamId = team.getTeam_ID();
        Team updatedTeam = teamDao.getTeamById(teamId);
        updatedTeam.setName("TeamTestUpdate");
        boolean updated = teamDao.updateTeam(updatedTeam);
        Assertions.assertTrue(updated);
        Assertions.assertEquals(team.getTeam_ID(), updatedTeam.getTeam_ID());
        Assertions.assertNotEquals(team.getName(),updatedTeam.getName());
    }

    @Test
    void testDeleteTeam() {
    	TeamDao teamDao = TeamDao.getInstance();
        Team team = new Team();
        team.setName("TeamTest");
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);
        int teamId = team.getTeam_ID();
        boolean deleted = teamDao.deleteTeam(team);
        Assertions.assertTrue(deleted);
        Team deletedTeam = teamDao.getTeamById(teamId);
        Assertions.assertEquals(deletedTeam.getTeam_ID(), 0);
    }

    @Test
    void testGetTeamById() {
    	TeamDao teamDao = TeamDao.getInstance();
        Team team = new Team();
        team.setName("TeamTest");
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);
        int teamId = team.getTeam_ID();
        Team retrievedTeam = teamDao.getTeamById(teamId);
        Assertions.assertEquals(team.getTeam_ID(), retrievedTeam.getTeam_ID());
    }

}

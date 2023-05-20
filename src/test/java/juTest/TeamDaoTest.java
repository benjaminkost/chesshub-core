package juTest;

import java.util.List;

import businessObjects.Club;
import businessObjects.User;
import dao.ClubDao;
import dao.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import businessObjects.Team;
import dao.TeamDao;

/**
 * Tests for the TeamDao class.
 */
public class TeamDaoTest {

    /**
     * Test case for inserting a team into the database.
     */
    @Test
    void testInsertTeam() {
        // Create instances of necessary DAO classes
        TeamDao teamDao = TeamDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        ClubDao clubDao = ClubDao.getInstance();

        // Create a team leader user
        User teamleader = new User();
        teamleader.setFirstname("Ben");
        teamleader.setLastname("Chester");
        teamleader.setEmail("janedoe@test.com");
        teamleader.setPassword("test456");
        userDao.insertUser(teamleader);

        // Create a club president user
        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("CEO");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        userDao.insertUser(president);

        // Create a club
        Club club = new Club();
        club.setName("Test Club");
        club.setPresident(president);
        clubDao.insertClub(club);

        // Create a team
        Team team = new Team();
        team.setName("TeamTest");
        team.setLeader(teamleader);
        team.setClub(club);

        // Insert the team into the database
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);

        // Retrieve the inserted team from the database
        int teamId = team.getTeam_ID();
        Team insertedTeam = teamDao.getTeamById(teamId);

        // Assert that the retrieved team is the same as the inserted team
        Assertions.assertEquals(team.getTeam_ID(), insertedTeam.getTeam_ID());

        // Clean up by deleting the team, club, and users
        teamDao.deleteTeam(team);
        clubDao.deleteClub(club);
        userDao.deleteUser(teamleader);
        userDao.deleteUser(president);
    }

    /**
     * Test case for retrieving all teams from the database.
     */
    @Test
    void testGetAllTeams() {
        // Create an instance of the TeamDao class
        TeamDao teamDao = TeamDao.getInstance();

        // Retrieve all teams from the database
        List<Team> teamList = teamDao.getAllTeams();

        // Assert that the team list is not null and not empty
        Assertions.assertNotNull(teamList);
        Assertions.assertFalse(teamList.isEmpty());
    }

    /**
     * Test case for updating a team in the database.
     */
    @Test
    void testUpdateTeam() {
        // Create instances of necessary DAO classes
        TeamDao teamDao = TeamDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        ClubDao clubDao = ClubDao.getInstance();

        // Create a team leader user
        User teamleader = new User();
        teamleader.setFirstname("Ben");
        teamleader.setLastname("Chester");
        teamleader.setEmail("janedoe@test.com");
        teamleader.setPassword("test456");
        userDao.insertUser(teamleader);

        // Create a club president user
        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("CEO");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        userDao.insertUser(president);

        // Create a club
        Club club = new Club();
        club.setName("Test Club");
        club.setPresident(president);
        clubDao.insertClub(club);

        // Create a team
        Team team = new Team();
        team.setName("TeamTest");
        team.setLeader(teamleader);
        team.setClub(club);

        // Insert the team into the database
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);

        // Retrieve the inserted team from the database
        int teamId = team.getTeam_ID();
        Team updatedTeam = teamDao.getTeamById(teamId);

        // Update the team's name
        updatedTeam.setName("TeamTestUpdate");

        // Update the team in the database
        boolean updated = teamDao.updateTeam(updatedTeam);
        Assertions.assertTrue(updated);

        // Assert that the updated team is still the same team and the name has changed
        Assertions.assertEquals(team.getTeam_ID(), updatedTeam.getTeam_ID());
        Assertions.assertNotEquals(team.getName(), updatedTeam.getName());

        // Clean up by deleting the team, club, and users
        teamDao.deleteTeam(team);
        clubDao.deleteClub(club);
        userDao.deleteUser(teamleader);
        userDao.deleteUser(president);
    }

    /**
     * Test case for deleting a team from the database.
     */
    @Test
    void testDeleteTeam() {
        // Create instances of necessary DAO classes
        TeamDao teamDao = TeamDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        ClubDao clubDao = ClubDao.getInstance();

        // Create a team leader user
        User teamleader = new User();
        teamleader.setFirstname("Ben");
        teamleader.setLastname("Chester");
        teamleader.setEmail("janedoe@test.com");
        teamleader.setPassword("test456");
        userDao.insertUser(teamleader);

        // Create a club president user
        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("CEO");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        userDao.insertUser(president);

        // Create a club
        Club club = new Club();
        club.setName("Test Club");
        club.setPresident(president);
        clubDao.insertClub(club);

        // Create a team
        Team team = new Team();
        team.setName("TeamTest");
        team.setLeader(teamleader);
        team.setClub(club);

        // Insert the team into the database
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);

        // Delete the team from the database
        boolean deleted = teamDao.deleteTeam(team);
        Assertions.assertTrue(deleted);

        // Verify that the team no longer exists in the database
        int teamId = team.getTeam_ID();
        Team deletedTeam = teamDao.getTeamById(teamId);
        Assertions.assertEquals(deletedTeam.getTeam_ID(), 0);

        // Clean up by deleting the club and users
        clubDao.deleteClub(club);
        userDao.deleteUser(teamleader);
        userDao.deleteUser(president);
    }

    /**
     * Test case for retrieving a team by its ID from the database.
     */
    @Test
    void testGetTeamById() {
        // Create instances of necessary DAO classes
        TeamDao teamDao = TeamDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        ClubDao clubDao = ClubDao.getInstance();

        // Create a team leader user
        User teamleader = new User();
        teamleader.setFirstname("Ben");
        teamleader.setLastname("Chester");
        teamleader.setEmail("janedoe@test.com");
        teamleader.setPassword("test456");
        userDao.insertUser(teamleader);

        // Create a club president user
        User president = new User();
        president.setFirstname("Ben");
        president.setLastname("CEO");
        president.setEmail("janedoe@test.com");
        president.setPassword("test456");
        userDao.insertUser(president);

        // Create a club
        Club club = new Club();
        club.setName("Test Club");
        club.setPresident(president);
        clubDao.insertClub(club);

        // Create a team
        Team team = new Team();
        team.setName("TeamTest");
        team.setLeader(teamleader);
        team.setClub(club);

        // Insert the team into the database
        boolean result = teamDao.insertTeam(team);
        Assertions.assertTrue(result);

        // Retrieve the team by its ID from the database
        int teamId = team.getTeam_ID();
        Team retrievedTeam = teamDao.getTeamById(teamId);

        // Assert that the retrieved team is the same as the inserted team
        Assertions.assertEquals(team.getTeam_ID(), retrievedTeam.getTeam_ID());

        // Clean up by deleting the team, club, and users
        teamDao.deleteTeam(team);
        clubDao.deleteClub(club);
        userDao.deleteUser(teamleader);
        userDao.deleteUser(president);
    }
}

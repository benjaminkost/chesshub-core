package Management;

import BusinessObjects.*;
import DAO.*;
import java.util.*;
import static Management.TeamManagement.removeTeam;

public class ClubManagement {

    /**
     * This method removes a club including all his teams
     *
     * @param club - club which should be deleted
     *
     * @author Lukas Zander
     */
    public static void removeClub(Club club){
        //First: Deleting all Teams
        for (Team t : getAllTeamsOfClub(club)){
            removeTeam(t);
        }
        //Second: Delete club
        ClubDao.getInstance().deleteClub(club);
    }

    /**
     * This method changes the president of a club
     *
     * @param club - club, which gets updated with a new leader
     * @param newPresident - given User
     *
     * @author Lukas Zander
     */
    public static void changeClubPresident(Club club, User newPresident){
        Club update = club;
        update.setPresident(newPresident);

        ClubDao.getInstance().updateClub(update);
    }

    /**
     * Returns all Teams of a given club
     *
     * @param club - club, which teams are requested
     * @return List<Team>, including all teams of the club
     *
     * @author Lukas Zander
     */
    public static List<Team> getAllTeamsOfClub(Club club){
        List<Team> teamsOfClub = new ArrayList<>();
        for(Team t : TeamDao.getInstance().getAllTeams()){
            if (t.getClub().getClub_ID()==club.getClub_ID()){
                teamsOfClub.add(t);
            }
        }
        return teamsOfClub;
    }

    public static List<Club> getAllClubs(){
        return ClubDao.getInstance().getAllClubs();
    }

    /**
     * This method returns the club, a given user manage (if exists)
     *
     * @param userID - search parameter for user/leader
     * @return managed club of identified user, if exists; else null1
     *
     * @author Lukas Zander
     */
    public static Club getManagedClubByUserID(int userID){
        for(Club c: ClubDao.getInstance().getAllClubs()){
            if(c.getPresident().getUser_Id()==userID){
                return c;
            }
        }
        return null;
    }

    /**
     * This method returns true, if a given name is already used as club name
     *
     * @param newName - given name
     * @return if name is used
     *
     * @author Lukas Zander
     */
    public static boolean clubNameAlreadyExists (String newName){
        for(Club c: getAllClubs()){
            if(c.getName().equals(newName)){
                return true;
            }
        }
        return false;
    }

    public static void createClub(Club newClub){
        ClubDao.getInstance().insertClub(newClub);
    }

    /**
     * This method returns a club, identified by its ID
     *
     * @param ID - search parameter
     * @return club, if exists; else null
     *
     * @author Lukas Zander
     */
    public static Club getClubByID(int ID){
        for (Club c: getAllClubs()){
            if (c.getClub_ID()==ID){
                return c;
            }
        }
        return null;
    }
}

package Management;

import BusinessObjects.*;
import DAO.*;
import java.util.*;
import static Management.TeamManagement.removeTeam;

public class ClubManagement {

    public static void addTeamToClub(Club club, Team newTeam){
        TeamDao.getInstance().getTeamById(newTeam.getTeam_ID()).setClub(club);
    }

    public static void removeAllTeamsFromClub(Club club){
        for (Team t : getAllTeamsOfClub(club)){
            removeTeam(t);
        }
    }

    public static void changeClubPresident(Club club, User newPresident){
        ClubDao.getInstance().getClubById(club.getClub_ID()).setPresident(newPresident);
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

    public static List<User> getAllPresidents(){
        List<User> presidents = new ArrayList<>();
        for (Club c : ClubDao.getInstance().getAllClubs()){
            presidents.add(c.getPresident());
        }
        return presidents;
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
}

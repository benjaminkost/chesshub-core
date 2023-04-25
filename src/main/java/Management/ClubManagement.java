package Management;

import BusinessObjects.Club;
import BusinessObjects.Team;
import BusinessObjects.User;
import dao.ClubDao;

import java.util.ArrayList;
import java.util.List;

public class ClubManagement {

    public static void addTeamToClub(Club club, Team newTeam){
        ClubDao.getInstance().getClubById(club.getClub_ID()).addTeam(newTeam);
    }

    public static void removeTeamFromClub(Club club, Team oldTeam){
        ClubDao.getInstance().getClubById(club.getClub_ID()).removeTeam(oldTeam);
    }

    public static void removeAllTeamsFromClub(Club club){
        ClubDao.getInstance().getClubById(club.getClub_ID()).setTeams(null);
    }

    public static void changeClubPresident(Club club, User newPresident){
        ClubDao.getInstance().getClubById(club.getClub_ID()).setPresident(newPresident);
    }

    public static List<Team> getAllTeamsOfClub(Club club){
        return ClubDao.getInstance().getClubById(club.getClub_ID()).getTeams();
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
}

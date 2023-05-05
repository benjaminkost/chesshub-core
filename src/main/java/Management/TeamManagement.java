package Management;

import BusinessObjects.*;
import DAO.*;
import java.util.List;

import static Management.ClubManagement.getAllTeamsOfClub;

public class TeamManagement {

    public static List<Team> getAllTeams(){
        return TeamDao.getInstance().getAllTeams();
    }

    public static boolean teamNameAlreadyExistsInClub (Club club, String newTeamName){
        for(Team t: getAllTeamsOfClub(club)){
            if (t.getName().equals(newTeamName)){
                return true;
            }
        }
        return false;
    }

    public static void addTeam(Team newTeam){
        TeamDao.getInstance().insertTeam(newTeam);
    }

    public static void removeTeam(Team oldTeam){
        for(User u : UserTeamDao.getInstance().getUsersByTeamId(oldTeam)){
            UserTeamDao.getInstance().deleteUserFromTeam(u, oldTeam);
        }

        TeamDao.getInstance().deleteTeam(oldTeam);
    }

    public static void updateTeam(Team updatedTeam){
        TeamDao.getInstance().updateTeam(updatedTeam);
    }

    public static void addMemberToTeam(Team team, User newMember){
        UserTeamDao.getInstance().insertUserInTeam(newMember,team);
    }

    public static void removeMemberFromTeam(Team team, User oldMember){
        UserTeamDao.getInstance().deleteUserFromTeam(oldMember,team);
    }

    /**
     * This method changes the leader of an existing team
     *
     * @param team - team, which gets a new leader
     * @param newTeamLeader - user, who will lead the given team
     *
     * @author Lukas Zander
     */
    public static void changeTeamLeader(Team team, User newTeamLeader){
        Team update = team;
        update.setLeader(newTeamLeader);

        TeamDao.getInstance().updateTeam(update);
    }

    /**
     * This method returns the team, which the given user leads
     *
     * @param userID - search string of potential team leader
     * @return leaded Team if exists, else null
     *
     * @author Lukas Zander
     */
    public static Team getManagedTeamByUserID(int userID){
        for(Team t: TeamDao.getInstance().getAllTeams()){
            if(t.getLeader().getUser_Id()==userID){
                return t;
            }
        }
        return null;
    }

    public static List<User> getUsersFromTeam(Team t){
        return UserTeamDao.getInstance().getUsersByTeamId(t);
    }

    public static boolean isUserPartofTeam(User u, Team t){
        for (Team userT : UserTeamDao.getInstance().getTeamsByUserId(u.getUser_Id())){
            if (userT.getTeam_ID() == t.getTeam_ID()){
                return true;
            }
        }
        return false;

    }

    /**
     * This method returns a team, identified by given ID
     *
     * @param ID - search parameter for team, given by user
     * @return matching team if exists, else null
     *
     * @author Lukas Zander
     */
    public static Team getTeamByID(int ID){
        for(Team t: TeamDao.getInstance().getAllTeams()){
            if(t.getTeam_ID()==ID){
                return t;
            }
        }
        return null;
    }
}

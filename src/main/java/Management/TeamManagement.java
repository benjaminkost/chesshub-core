package Management;

import BusinessObjects.Club;
import BusinessObjects.Team;
import BusinessObjects.User;
import dao.TeamDao;
import dao.UserDao;
import dao.UserTeamDao;

import java.util.ArrayList;
import java.util.List;

public class TeamManagement {

    public static List<Team> getAllTeams(){
        return TeamDao.getInstance().getAllTeams();
    }

    public static void addTeam(Team newTeam){
        TeamDao.getInstance().insertTeam(newTeam);
    }

    public static void removeTeam(Team oldTeam){
        TeamDao.getInstance().deleteTeam(oldTeam);
    }

    public static void updateTeam(Team updatedTeam){
        TeamDao.getInstance().updateTeam(updatedTeam);
    }

    public static void addMemberToTeam(Team team, User newMember){
        UserTeamDao.getInstance().insertUserInTeam(newMember,team);
        //TeamDao.getInstance().getTeamById(team.getTeam_ID()).addMember(newMember);
    }

    public static void removeMemberFromTeam(Team team, User oldMember){
        UserTeamDao.getInstance().deleteUserFromTeam(oldMember,team);
        //TeamDao.getInstance().getTeamById(team.getTeam_ID()).removeMember(oldMember);
    }

    public static void removeAllMembersFromTeam(Team team){
        for(User u : UserTeamDao.getInstance().getUsersByTeamId(team)){
            UserTeamDao.getInstance().deleteUserFromTeam(u, team);
        }
        //TeamDao.getInstance().getTeamById(team.getTeam_ID()).setMembers(null);
    }

    public static void changeTeamLeader(Team team, User newTeamLeader){
        TeamDao.getInstance().getTeamById(team.getTeam_ID()).setLeader(newTeamLeader);
    }

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
        for (Team userT : UserTeamDao.getInstance().getTeamsByUserId(u)){
            if (userT == t){
                return true;
            }
        }
        return false;

    }







}

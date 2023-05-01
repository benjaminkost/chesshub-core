package Management;

import BusinessObjects.*;
import DAO.*;


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
    }

    public static void removeMemberFromTeam(Team team, User oldMember){
        UserTeamDao.getInstance().deleteUserFromTeam(oldMember,team);
    }

    public static void removeAllMembersFromTeam(Team team){
        for(User u : UserTeamDao.getInstance().getUsersByTeamId(team)){
            UserTeamDao.getInstance().deleteUserFromTeam(u, team);
        }
    }

    public static void changeTeamLeader(Team team, User newTeamLeader){
        Team update = team;
        update.setLeader(newTeamLeader);

        TeamDao.getInstance().updateTeam(update);
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

    public static Team getTeamByID(int ID){
        for(Team t: TeamDao.getInstance().getAllTeams()){
            if(t.getTeam_ID()==ID){
                return t;
            }
        }

        return null;
    }







}

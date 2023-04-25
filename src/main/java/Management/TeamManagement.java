package Management;

import BusinessObjects.Club;
import BusinessObjects.Team;
import BusinessObjects.User;
import dao.TeamDao;
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
        TeamDao.getInstance().getTeamById(team.getTeam_ID()).addMember(newMember);
    }

    public static void removeMemberFromTeam(Team team, User oldMember){
        TeamDao.getInstance().getTeamById(team.getTeam_ID()).removeMember(oldMember);
    }

    public static void removeAllMembersFromTeam(Team team){
        TeamDao.getInstance().getTeamById(team.getTeam_ID()).setMembers(null);
    }

    public static void changeTeamLeader(Team team, User newTeamLeader){
        TeamDao.getInstance().getTeamById(team.getTeam_ID()).setLeader(newTeamLeader);
    }






}
